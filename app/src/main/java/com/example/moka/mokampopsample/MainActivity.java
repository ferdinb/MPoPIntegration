package com.example.moka.mokampopsample;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.starmicronics.starioextension.commandbuilder.ISCBBuilder;
import com.starmicronics.starioextension.commandbuilder.SCBAlignment;
import com.starmicronics.starioextension.commandbuilder.SCBFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public static enum TYPE {
        STARIO, ZONERICH, MPOP
    }

    private Printer p;
    private Reports.Details reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String jsonString = getJson();
        Gson gson = new Gson();
        Reports r = gson.fromJson(jsonString, Reports.class);
        Log.e("f.ninaber", "r : " + r);
        if (r != null) {
            reports = r.getResults().get(0);

            if (reports.getCheckouts() != null && reports.getCheckouts().size() > 0) {
                String checkouts = gson.toJson(reports.getCheckouts());
                reports.setJsonCheckout(checkouts);
            }

            if (reports.getPayment_discounts() != null && reports.getPayment_discounts().size() > 0) {
                String payment_discount = gson.toJson(reports.getPayment_discounts());
                reports.setJsonCashDiscount(payment_discount);
            }

            if (reports.getPayment_taxes() != null && reports.getPayment_taxes().size() > 0) {
                String payment_taxes = gson.toJson(reports.getPayment_taxes());
                reports.setJsonTax(payment_taxes);
            }

            if (reports.getPayment_gratuities() != null && reports.getPayment_gratuities().size() > 0) {
                String payment_gratuities = gson.toJson(reports.getPayment_gratuities());
                reports.setJsonGratuities(payment_gratuities);
            }
        }

        List<Printer> printers = findAvailablePrinter(this);
        TextView macTv = (TextView) findViewById(R.id.mac);
        if (printers != null && printers.size() > 0) {
            p = printers.get(0);
            macTv.setText(p.getName() + " - " + p.getMac());
        }


        Button printBtn = (Button) findViewById(R.id.print);
        printBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p != null) {
                    printReportMpop(MainActivity.this, reports, p);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Printer> findAvailablePrinter(Activity activity) {
        try {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Toast.makeText(activity, "No bluetooth adapter available", Toast.LENGTH_SHORT).show();
            }

            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            List<Printer> devices = new ArrayList<>();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    if (device.getName().contains("STAR mPOP")) {
                        devices.add(addItemPrinter(device.getName(), device.getAddress(), "Disconnected", TYPE.MPOP));
                    }
                }
            }
            return devices;
        } catch (Exception e) {
            Log.e("f.ninaber", "error find printer : " + e);
            return null;
        }


    }


    public static Printer addItemPrinter(String name, String mac, String status, TYPE type) {
        Printer p = new Printer();
        p.setName(name);
        p.setMac(mac);
        p.setStatus(status);
        p.setType(type.toString());
        p.setIsVisible(true);
        return p;
    }


    public void printReportMpop(final Context context, final Reports.Details report, final Printer printer) {
        AsyncTask<Void, Void, Communication.Result> task = new AsyncTask<Void, Void, Communication.Result>() {
            @Override
            protected Communication.Result doInBackground(Void... params) {
                byte[] data = createCommandsEnglish2inchLineModeReceipt(context, report, true);
                return Communication.sendCommands(data, "BT:" + printer.getMac(), "", 20000, context);     // 10000mS!!!
            }

            @Override
            protected void onPostExecute(Communication.Result result) {
                String msg = null;
                switch (result) {
                    case Success:
                        msg = "Success!";
                        break;
                    case ErrorOpenPort:
                        msg = "Fail to openPort";
                        break;
                    case ErrorBeginCheckedBlock:
                        msg = "Printer is offline";
                        break;
                    case ErrorEndCheckedBlock:
                        msg = "Printer is offline";
                        break;
                    case ErrorReadPort:
                        msg = "Read port error";
                        break;
                    case ErrorWritePort:
                        msg = "Write port error";
                        break;
                    default:
                        msg = "Unknown error";
                        break;
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
    }

    public byte[] createCommandsEnglish2inchLineModeReceipt(Context context, Reports.Details report, boolean isMPOP) {
        int sizeA = 38;
        int sizeB = 32;
        int sizeC = 15;
        int sizeD = 30;

        if (!isMPOP) {
            sizeA = 54;
            sizeB = 48;
            sizeC = 31;
            sizeD = 46;
        }

        CommandDataList commands = new CommandDataList();
        commands.add(0x1b, 0x20, 0x00);                     // ANK Right Space

        Bitmap bitmap = getBitmapLogo();
        if (null != bitmap) {
            ISCBBuilder builder = SCBFactory.createBuilder(SCBFactory.Emulation.Star);
            builder.appendAlignmentBitmap(bitmap, false, 180, SCBAlignment.Position.Center);
            List<byte[]> listBuf = builder.getBuffer();
            for (byte[] buf : listBuf) {
                commands.add(buf);
            }
            commands.add("\r\n");
            commands.add("\r\n");
        }

        commands.add(0x1b, 0x1d, 0x61, 0x01);               // Alignment(center)

        String address = "Jl. Kebun Jeruk no 88";
        String city = "Jakarta Barat";
        String zip = "11480";
        String province = "DKI Jakarta";
        String phone = "+6281123456789";


        String name = "Moka Teknologi Indonesia";
        String outletNotes = "This is bussiness note";

        /*Name & Address*/
        commands.add(0x1b, 0x45);                           // Set Bold

        if (!isMPOP) {
            commands.add(0x06, 0x09, 0x1b, 0x69, 0x01, 0x01); // Character expansion
        }
        commands.add(name + "\r\n");
        if (!isMPOP) {
            commands.add(0x1b, 0x69, 0x00, 0x00); // Cancel Character Expansion
        }

        commands.add(0x1b, 0x46);                           // Cancel Bold
        commands.add(address + ", " + city + ", " + province + ", " + zip + "\r\n");
        commands.add(phone + "\r\n\r\n");

        /*Date*/
        commands.add(0x1b, 0x1d, 0x61, 0x00);               // Alignment(left)
        commands.add(0x1b, 0x44, 0x02, 0x10, 0x00);         // SetHT
        long timestamp = System.currentTimeMillis();
        String day = report.getTransaction_date();
        String time = report.getTransaction_time();
        if (timestamp >= Long.MAX_VALUE - 1000000) {
            day = report.getTransaction_date();
            time = report.getTransaction_time();
        }

        StringBuilder dateBuilder = new StringBuilder();
        int dayLength = day.length();
        int timeLength = day.length();

        int totalLength = sizeA - (dayLength + timeLength);
        dateBuilder.append(day);
        for (int i = 0; i < totalLength; i++) {
            dateBuilder.append(" ");
        }
        dateBuilder.append(time);
        commands.add(dateBuilder.toString() + "\r\n");

        /*Receipt Number*/
        StringBuilder receiptNumberBuilder = new StringBuilder();
        receiptNumberBuilder.append("Receipt Number");
        int receiptLength = sizeB - (receiptNumberBuilder.length() + report.getPayment_no().length());
        for (int i = 0; i < receiptLength; i++) {
            receiptNumberBuilder.append(" ");
        }
        receiptNumberBuilder.append(report.getPayment_no());
        commands.add(receiptNumberBuilder.toString() + "\r\n");

        /*Served by*/
        String employeeName = report.getServed_by();
        if (!TextUtils.isEmpty(employeeName)) {
            StringBuilder servedBuilder = new StringBuilder();
            servedBuilder.append("Served By");

            int employeeLength = employeeName.length() + servedBuilder.length();
            employeeName = employeeLength >= sizeB ? employeeName.substring(0, employeeName.length() - (employeeLength - sizeB) - 3) + "..." : employeeName;

            employeeLength = sizeB - (servedBuilder.length() + employeeName.length());
            for (int i = 0; i < employeeLength; i++) {
                servedBuilder.append(" ");
            }
            servedBuilder.append(employeeName);
            commands.add(servedBuilder.toString() + "\r\n");
        }

        /*Collected by*/
        StringBuilder collectedBuilder = new StringBuilder();
        collectedBuilder.append("Collected BY");
        String username = "Teguh Setiawan";
        int collectedLength = username.length() + collectedBuilder.length();
        username = collectedLength >= sizeB ? username.substring(0, username.length() - (collectedLength - sizeB) - 3) + "..." : username;

        collectedLength = sizeB - (collectedBuilder.length() + username.length());
        for (int i = 0; i < collectedLength; i++) {
            collectedBuilder.append(" ");
        }
        collectedBuilder.append(username);
        commands.add(collectedBuilder.toString() + "\r\n");

        /*Line*/
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < sizeB; i++) {
            line.append("-");
        }
        commands.add(line.toString() + "\r\n");

        /*Item*/
        Gson gson = new Gson();
        String jsonCheckout = report.getJsonCheckout();
        Type t = new TypeToken<List<Reports.Checkouts>>() {
        }.getType();
        List<Reports.Checkouts> items = gson.fromJson(jsonCheckout, t);
        for (int x = 0; x < items.size(); x++) {
            StringBuilder itemBuilder = new StringBuilder();
            Reports.Checkouts item = items.get(x);
            String itemName = item.getItem_name();
            String quantity = "x" + item.getQuantity();

            long priceLong = item.getPrice();
            String price = formatRp(context, priceLong);
            if (itemName.length() + quantity.length() >= sizeC) {
                int excess = (itemName.length() + quantity.length()) - sizeC;
                if (itemName.length() > excess + 2) {
                    itemName = itemName.substring(0, itemName.length() - (excess + 2)) + "..";
                }
                itemBuilder.append(itemName);
                itemBuilder.append(quantity);
            } else {
                itemBuilder.append(itemName);
                int remaining = sizeC - (itemName.length() + quantity.length());
                for (int i = 0; i < remaining; i++) {
                    itemBuilder.append(" ");
                }
                itemBuilder.append(quantity);
            }

            if (itemBuilder.length() + price.length() <= sizeB) {
                int remaining = sizeB - (itemBuilder.length() + price.length());
                for (int i = 0; i < remaining; i++) {
                    itemBuilder.append(" ");
                }
            }
            itemBuilder.append(price);
            commands.add(0x1b, 0x45);                           // Set Bold
            commands.add(itemBuilder.toString() + "\r\n");
            commands.add(0x1b, 0x46);                           // Cancel Bold

            /*Item Variant*/
            if (!TextUtils.isEmpty(item.getItem_variant_name()) && !item.getItem_variant_name().equalsIgnoreCase(item.getItem_name())) {
                commands.add(item.getItem_variant_name() + "\r\n");
            }

            /*Modifiers*/
            List<Reports.Modifiers> modifierses = item.getModifiers();
            if (modifierses != null && modifierses.size() > 0) {
                for (int i = 0; null != modifierses && i < modifierses.size(); i++) {
                    Reports.Modifiers reportModifiersOption = modifierses.get(i);
                    StringBuilder modifierBuilder = new StringBuilder();
                    String modifierOptionName = reportModifiersOption.getModifier_option_name();
                    String modifierName = reportModifiersOption.getModifier_name();
                    modifierBuilder.append(modifierName + " - " + modifierOptionName + " " + formatRp(context, reportModifiersOption.getPrice()));
                    commands.add(modifierBuilder.toString() + "\r\n");
                }
            }

            /*Discounts*/
            List<Reports.Discounts> discountses = item.getDiscounts();
            if (discountses != null && discountses.size() > 0) {
                for (int i = 0; null != discountses && i < discountses.size(); i++) {
                    Reports.Discounts reportDiscounts = discountses.get(i);
                    StringBuilder discountBuilder = new StringBuilder();
                    String discountName = reportDiscounts.getDiscount_name();
                    long totalDiscount = 0;
                    if (reportDiscounts.getDiscount_type().equalsIgnoreCase("percentage")) {
                        String ps = reportDiscounts.getDiscount_percentage() % 1 != 0 ? String.valueOf(reportDiscounts.getDiscount_percentage()) : String.valueOf((long) reportDiscounts.getDiscount_percentage());
                        discountName = discountName + " (" + ps.replace('.', ',') + "%)";
                        totalDiscount = reportDiscounts.getDiscount_amount();
                    }
                    discountBuilder.append(discountName);

                    String discountPrice = "-" + formatRp(context, totalDiscount);
                    if (discountBuilder.length() + discountPrice.length() <= sizeB) {
                        int remaining = sizeB - (discountBuilder.length() + discountPrice.length());
                        for (int z = 0; z < remaining; z++) {
                            discountBuilder.append(" ");
                        }
                    } else {
                        String discountNm = discountBuilder.toString();
                        int excess = (discountNm.length() + discountPrice.length()) - sizeD;
                        if (discountNm.length() > excess) {
                            discountBuilder.delete(0, discountBuilder.length());
                            discountNm = discountNm.substring(0, discountNm.length() - excess);
                            discountBuilder.append(discountNm + "..");
                        }
                    }
                    discountBuilder.append(discountPrice);
                    commands.add(discountBuilder.toString() + "\r\n");
                }
            }

            /*Note*/
            if (!TextUtils.isEmpty(item.getNote())) {
                commands.add(item.getNote() + "\r\n");
            }
//            commands.add("\r\n");
        }

        /*Cash Discounts*/
        String jsonCashDiscount = report.getJsonCashDiscount();
        if (!TextUtils.isEmpty(jsonCashDiscount)) {
            Type discountCashType = new TypeToken<ArrayList<ReportDiscount>>() {
            }.getType();
            List<ReportDiscount> reportDiscounts = new Gson().fromJson(report.getJsonCashDiscount(), discountCashType);
            for (int i = 0; reportDiscounts != null && i < reportDiscounts.size(); i++) {
                ReportDiscount discountCash = reportDiscounts.get(i);
                StringBuilder discountBuilder = new StringBuilder();
                String discountName = discountCash.getDiscount_name();
                discountBuilder.append(discountName);

                String discountPrice = "-" + formatRp(context, discountCash.getDiscount_amount());
                if (discountBuilder.length() + discountPrice.length() <= sizeB) {
                    int remaining = sizeB - (discountBuilder.length() + discountPrice.length());
                    for (int z = 0; z < remaining; z++) {
                        discountBuilder.append(" ");
                    }
                } else {
                    String discountNm = discountBuilder.toString();
                    int excess = (discountNm.length() + discountPrice.length()) - sizeD;
                    if (discountNm.length() > excess) {
                        discountNm = discountNm.substring(0, discountNm.length() - excess);
                        discountBuilder.delete(0, discountBuilder.length());
                        discountBuilder.append(discountNm + "..");
                    }
                }
                discountBuilder.append(discountPrice);
                commands.add(discountBuilder.toString() + "\r\n");
            }
        }
        /*Line*/
        commands.add(line.toString() + "\r\n");

        /*Subtotal*/
        StringBuilder subTotalBuilder = new StringBuilder();
        String subtotal = "Subtotal";
        String totalPrice = formatRp(context, report.getSubtotal());

        subTotalBuilder.append(subtotal);
        if (subtotal.length() + totalPrice.length() <= sizeB) {
            int remaining = sizeB - (subtotal.length() + totalPrice.length());
            for (int m = 0; m < remaining; m++) {
                subTotalBuilder.append(" ");
            }
        }
        subTotalBuilder.append(totalPrice);
        commands.add(subTotalBuilder.toString() + "\r\n");

        /*Gratuities*/
        if (report.getJsonGratuities() != null && report.isEnable_gratuity()) {
            Type gratuityType = new TypeToken<ArrayList<ReportGratuity>>() {
            }.getType();
            List<ReportGratuity> reportGratuities = new Gson().fromJson(report.getJsonGratuities(), gratuityType);
            for (int i = 0; reportGratuities != null && i < reportGratuities.size(); i++) {
                ReportGratuity gratuityReport = reportGratuities.get(i);

                if (!report.isInclude_gratuity_tax()) {
                    StringBuilder gratuityBuilder = new StringBuilder();
                    String gratuityPrice = formatRp(context, gratuityReport.getTotal());
                    String gString = gratuityReport.getAmount() % 1 == 0 ? String.valueOf((long) gratuityReport.getAmount()) : String.valueOf(gratuityReport.getAmount());
                    gratuityBuilder.append(gratuityReport.getName() + " (" + gString.replace('.', ',') + "%)");
                    if (gratuityBuilder.length() + gratuityPrice.length() <= sizeB) {
                        int remaining = sizeB - (gratuityBuilder.length() + gratuityPrice.length());
                        for (int m = 0; m < remaining; m++) {
                            gratuityBuilder.append(" ");
                        }
                    }
                    gratuityBuilder.append(gratuityPrice);
                    commands.add(gratuityBuilder.toString() + "\r\n");
                } else {
                    commands.add(gratuityReport.getName() + " (Included)" + "\r\n");
                }
            }
        }

        /*Taxes*/
        if (report.getJsonTax() != null && report.isEnable_tax()) {
            Type taxType = new TypeToken<ArrayList<ReportTax>>() {
            }.getType();
            List<ReportTax> reportTaxes = new Gson().fromJson(report.getJsonTax(), taxType);
            for (int i = 0; reportTaxes != null && i < reportTaxes.size(); i++) {
                ReportTax taxReport = reportTaxes.get(i);
                if (report.isInclude_gratuity_tax()) {
                    commands.add(taxReport.getName() + " (Included)" + "\r\n");
                } else {
                    StringBuilder taxBuilder = new StringBuilder();
                    String taxPrice = formatRp(context, taxReport.getTotal());
                    String tString = taxReport.getAmount() % 1 == 0 ? String.valueOf((long) taxReport.getAmount()) : String.valueOf(taxReport.getAmount());
                    taxBuilder.append(taxReport.getName() + " (" + tString + "%)");
                    if (taxBuilder.length() + taxPrice.length() <= sizeB) {
                        int remaining = sizeB - (taxBuilder.length() + taxPrice.length());
                        for (int m = 0; m < remaining; m++) {
                            taxBuilder.append(" ");
                        }
                    }
                    taxBuilder.append(taxPrice);
                    commands.add(taxBuilder.toString() + "\r\n");
                }
            }
        }

        /*Line*/
        commands.add(line.toString() + "\r\n");

        /*Total*/
        StringBuilder totalBuilder = new StringBuilder();
        totalBuilder.append("Total");

        String finalPriceString = formatRp(context, report.getTotal_collected());
        if (totalBuilder.length() + finalPriceString.length() <= sizeB) {
            int remaining = sizeB - (totalBuilder.length() + finalPriceString.length());
            for (int m = 0; m < remaining; m++) {
                totalBuilder.append(" ");
            }
        }
        totalBuilder.append(finalPriceString);
        commands.add(0x1b, 0x45);                           // Set Bold
        commands.add(totalBuilder.toString() + "\r\n");
        commands.add(0x1b, 0x46);                           // Cancel Bold

        /*Payment type*/
        if (report.getPayment_type().equalsIgnoreCase("Cash")) {
            /*Line*/
            commands.add(line.toString() + "\r\n");

            /*Cash*/
            StringBuilder cashBuilder = new StringBuilder();
            cashBuilder.append("Cash");
            String cashPrice = formatRp(context, report.getTendered());
            if (cashBuilder.length() + cashPrice.length() <= sizeB) {
                int remaining = sizeB - (cashBuilder.length() + cashPrice.length());
                for (int m = 0; m < remaining; m++) {
                    cashBuilder.append(" ");
                }
            }
            cashBuilder.append(cashPrice);
            commands.add(cashBuilder.toString() + "\r\n");

            /*Change*/
            StringBuilder changeBuilder = new StringBuilder();
            changeBuilder.append("Change");
            String changePrice = formatRp(context, report.getChange());
            if (changeBuilder.length() + changePrice.length() <= sizeB) {
                int remaining = sizeB - (changeBuilder.length() + changePrice.length());
                for (int m = 0; m < remaining; m++) {
                    changeBuilder.append(" ");
                }
            }
            changeBuilder.append(changePrice);
            commands.add(changeBuilder.toString() + "\r\n");
        } else {
             /*Other Payment*/
            String paymentType = report.getPayment_type_label();
            StringBuilder cashBuilder = new StringBuilder();
            cashBuilder.append(paymentType);
            String cashPrice = formatRp(context, report.getTendered());
            if (cashBuilder.length() + cashPrice.length() <= sizeB) {
                int remaining = sizeB - (cashBuilder.length() + cashPrice.length());
                for (int m = 0; m < remaining; m++) {
                    cashBuilder.append(" ");
                }
            }
            cashBuilder.append(cashPrice);
            commands.add(cashBuilder.toString() + "\r\n");

             /*Credit Card*/
            if (!TextUtils.isEmpty(report.getPayment_type()) && report.getPayment_type().equalsIgnoreCase("Card") && !TextUtils.isEmpty(report.getCard_no())) {
                if (!TextUtils.isEmpty(report.getCard_type())) {
                    String ccNumber = report.getCard_type() + " - " + report.getCard_no();
                    commands.add(ccNumber + "\r\n");
                } else {
                    commands.add(report.getCard_no() + "\r\n");
                }
            }

             /*Payment Note*/
            if (!TextUtils.isEmpty(report.getPayment_note())) {
                commands.add(report.getPayment_note() + "\r\n");
            }
        }


        /*Line*/
        commands.add(line.toString() + "\r\n");

        /*Social Media*/
        boolean isLine = false;
        if (!TextUtils.isEmpty("website")) {
            try {
                Bitmap imgBitmap = BitmapFactory.decodeResource(context.getResources(), R.raw.globe_icon);
                Bitmap bmp = drawTextAndBitmap(imgBitmap, "website");
                StarBitmap starbitmap = new StarBitmap(bmp, false, bmp.getWidth());
                commands.add(starbitmap.getImageRasterDataForPrinting_graphic(true));
            } catch (Exception e) {
                e.printStackTrace();
            }
            isLine = true;
        }

        if (!TextUtils.isEmpty("Facebook")) {
            try {
                Bitmap imgBitmap = BitmapFactory.decodeResource(context.getResources(), R.raw.fb_icon);
                Bitmap bmp = drawTextAndBitmap(imgBitmap, "Facebook");
                StarBitmap starbitmap = new StarBitmap(bmp, false, bmp.getWidth());
                commands.add(starbitmap.getImageRasterDataForPrinting_graphic(true));
            } catch (Exception e) {
                e.printStackTrace();
            }
            isLine = true;
        }

        if (!TextUtils.isEmpty("Twitter")) {
            try {
                Bitmap imgBitmap = BitmapFactory.decodeResource(context.getResources(), R.raw.twitter_icon);
                Bitmap bmp = drawTextAndBitmap(imgBitmap, "Twitter");
                StarBitmap starbitmap = new StarBitmap(bmp, false, bmp.getWidth());
                commands.add(starbitmap.getImageRasterDataForPrinting_graphic(true));
            } catch (Exception e) {
                e.printStackTrace();
            }
            isLine = true;
        }

        if (!TextUtils.isEmpty("Instagram")) {
            try {
                Bitmap imgBitmap = BitmapFactory.decodeResource(context.getResources(), R.raw.insta_icon);
                Bitmap bmp = drawTextAndBitmap(imgBitmap, "Instagram");
                StarBitmap starbitmap = new StarBitmap(bmp, false, bmp.getWidth());
                commands.add(starbitmap.getImageRasterDataForPrinting_graphic(true));
            } catch (Exception e) {
                e.printStackTrace();
            }
            isLine = true;
        }

        /*Outlet Note*/
        if (!TextUtils.isEmpty(outletNotes)) {
            if (isLine) {
                commands.add(line.toString() + "\r\n");
            }

            commands.add(0x1b, 0x45);                           // Set Bold
            commands.add("Notes" + "\r\n");
            commands.add(0x1b, 0x46);                           // Cancel Bold
            commands.add(outletNotes + "\r\n");
        }

        /*Space*/
        commands.add("\r\n\r\n\r\n");

        commands.add(0x1b, 0x64, 0x03);                    // Cut
        return commands.getByteArray();
    }


    private Bitmap getBitmapLogo() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.logo);
    }

    public String formatRp(Context context, long amount) {
        NumberFormat numberFormatter = NumberFormat.getNumberInstance();
        String value = numberFormatter.format(amount).replace(",", ".");
        return context.getResources().getString(R.string.rp, value);
    }

    public String formatRpWithEnclosure(Context context, long amount) {
        NumberFormat numberFormatter = NumberFormat.getNumberInstance();
        String value = numberFormatter.format(amount).replace(",", ".");
        return "(" + context.getResources().getString(R.string.rp, value) + ")";

    }


    public Bitmap drawTextAndBitmap(Bitmap ic, String text) {
        Bitmap cs;
        Rect bounds = new Rect();
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLACK);
        p.setTextSize(22);
        p.getTextBounds(text, 0, text.length(), bounds);

        int width = ic.getWidth() + bounds.width();
        int height = ic.getHeight() > bounds.height() ? ic.getHeight() : bounds.height();

        cs = Bitmap.createBitmap(width + 20, height + 8, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(cs);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(ic, 0f, 0f, null);
        canvas.drawText(text, ic.getWidth() + 12, (int) (ic.getHeight() / 1.5), p);
        return cs;
    }

    private String getJson() {
        AssetManager assetManager = getAssets();
        InputStream input;
        String text = "";

        try {
            input = assetManager.open("report.txt");

            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // byte buffer into a string
            text = new String(buffer);

        } catch (IOException e) {
            Log.e("f.ninaber", "E : " + e.getMessage());
        }
        Log.e("f.ninaber", "Text File: " + text);
        return text;
    }

}
