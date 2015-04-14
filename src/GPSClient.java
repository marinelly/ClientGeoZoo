/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
//Packages for Location API
import javax.microedition.location.LocationException;
import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.LocationProvider;

//Packages for Communication
import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;
/**
 * @author Marinelly R
 */
public class GPSClient extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Form form;
    private ImageItem imageItem;
    private StringItem stringItem1;
    private StringItem stringItem;
    private StringItem stringItem2;
    private TextField textField1;
    private TextField textField;
    private List sugerencias;
    private Form informacion;
    private ImageItem imageItem1;
    private StringItem stringItem3;
    private Form Hastapronto;
    private StringItem stringItem4;
    private Command exitCommand;
    private Command searchPosition;
    private Command showInfo;
    private Command backList;
    private Command goTo;
    private Command changePos;
    private Command exitCommand1;
    private Image image;
    //</editor-fold>//GEN-END:|fields|0|

    private boolean midletStopped = false;
   
    private Thread myThread;
    Services service;
    Vector indArbol;
    Vector indBD;
    Hashtable infoExhibiciones;
    String visitados;
    /**
     * The GPSClient constructor.
     */
    public GPSClient() {
         indArbol = new Vector();
         visitados = "visit";
         indBD = new Vector();

    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
        // Include these lines inside the initialize method

        try {

        }
        catch (Exception e2)
        {
        	java.lang.System.out.println("Error getting Location Provider: " + e2);
        }

        searchPosition = new Command("Donde estoy", Command.OK, 0);//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|17-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|17-preAction
                // write pre-action user code here
                salir();




                switchDisplayable(null, getHastapronto());//GEN-LINE:|7-commandAction|2|17-postAction
                // write post-action user code here
            } else if (command == searchPosition) {//GEN-LINE:|7-commandAction|3|19-preAction
                // write pre-action user code here
                switchDisplayable(null, getSugerencias());//GEN-LINE:|7-commandAction|4|19-postAction
                // write post-action user code here
                service = new Services(textField.getString(), textField1.getString(), sugerencias, indArbol, indBD);
                infoExhibiciones = service.CargarExhibiciones();

                service.sendFix("list/-1");




            }//GEN-BEGIN:|7-commandAction|5|38-preAction
        } else if (displayable == informacion) {
            if (command == backList) {//GEN-END:|7-commandAction|5|38-preAction
                // write pre-action user code here
                switchDisplayable(null, getSugerencias());//GEN-LINE:|7-commandAction|6|38-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|7|63-preAction
                // write pre-action user code here
                salir();
                switchDisplayable(null, getHastapronto());//GEN-LINE:|7-commandAction|8|63-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|29-preAction
        } else if (displayable == sugerencias) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|9|29-preAction
                // write pre-action user code here
                visitados = visitados + "/"+indBD.elementAt(sugerencias.getSelectedIndex());
                service.sendFix("list/"+indArbol.elementAt(sugerencias.getSelectedIndex()));

                sugerenciasAction();//GEN-LINE:|7-commandAction|10|29-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|11|60-preAction
                // write pre-action user code here
                salir();
                switchDisplayable(null, getHastapronto());//GEN-LINE:|7-commandAction|12|60-postAction
                // write post-action user code here
            } else if (command == goTo) {//GEN-LINE:|7-commandAction|13|44-preAction
                // write pre-action user code here
                visitados = visitados + "/"+indBD.elementAt(sugerencias.getSelectedIndex());
                service.sendFix("list/"+indArbol.elementAt(sugerencias.getSelectedIndex()));



//GEN-LINE:|7-commandAction|14|44-postAction
                // write post-action user code here
            } else if (command == showInfo) {//GEN-LINE:|7-commandAction|15|33-preAction
                // write pre-action user code here
                switchDisplayable(null, getInformacion());//GEN-LINE:|7-commandAction|16|33-postAction
                // write post-action user code here
                boolean sw = false;
                int i = 1;
                 String info = "";
                String llave = sugerencias.getString(sugerencias.getSelectedIndex());

                 info = (String) this.infoExhibiciones.get(llave);

                stringItem3.setText(info);
                try {

                    image = Image.createImage("/"+llave + ".jpg");
                    this.imageItem1.setImage(image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }//GEN-BEGIN:|7-commandAction|17|7-postCommandAction
        }//GEN-END:|7-commandAction|17|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|18|
    //</editor-fold>//GEN-END:|7-commandAction|18|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("GEOZOO", new Item[] { getStringItem(), getImageItem(), getStringItem1(), getStringItem2(), getTextField(), getTextField1() });//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.addCommand(searchPosition);
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|16-getter|0|16-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|16-getter|1|16-postInit
            // write post-init user code here
        }//GEN-BEGIN:|16-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|16-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageItem ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of imageItem component.
     * @return the initialized component instance
     */
    public ImageItem getImageItem() {
        if (imageItem == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            imageItem = new ImageItem("", getImage(), ImageItem.LAYOUT_CENTER | Item.LAYOUT_TOP | Item.LAYOUT_BOTTOM | Item.LAYOUT_VCENTER, "<Missing Image>", Item.PLAIN);//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return imageItem;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: image ">//GEN-BEGIN:|21-getter|0|21-preInit
    /**
     * Returns an initiliazed instance of image component.
     * @return the initialized component instance
     */
    public Image getImage() {
        if (image == null) {//GEN-END:|21-getter|0|21-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|21-getter|1|21-@java.io.IOException
                image = Image.createImage("/logo3.jpg");
            } catch (java.io.IOException e) {//GEN-END:|21-getter|1|21-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|21-getter|2|21-postInit
            // write post-init user code here
        }//GEN-BEGIN:|21-getter|3|
        return image;
    }
    //</editor-fold>//GEN-END:|21-getter|3|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            stringItem = new StringItem("", "GeoZoo", Item.PLAIN);//GEN-BEGIN:|24-getter|1|24-postInit
            stringItem.setLayout(ImageItem.LAYOUT_CENTER);//GEN-END:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|24-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of stringItem1 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            stringItem1 = new StringItem("", "Bienvenido al", Item.PLAIN);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
            stringItem1 = new StringItem("", "Bienvenido al\n", Item.PLAIN);
        }//GEN-BEGIN:|25-getter|2|
        return stringItem1;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem2 ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of stringItem2 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem2() {
        if (stringItem2 == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            stringItem2 = new StringItem("", "Zool\u00F3gico de Barranquilla");//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return stringItem2;
    }
    //</editor-fold>//GEN-END:|26-getter|2|
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sugerencias ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of sugerencias component.
     * @return the initialized component instance
     */
    public List getSugerencias() {
        if (sugerencias == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            sugerencias = new List("Te sugerimos ir a...", Choice.IMPLICIT);//GEN-BEGIN:|27-getter|1|27-postInit
            sugerencias.addCommand(getShowInfo());
            sugerencias.addCommand(getGoTo());
            sugerencias.addCommand(getExitCommand());
            sugerencias.setCommandListener(this);//GEN-END:|27-getter|1|27-postInit
            // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return sugerencias;
    }
    //</editor-fold>//GEN-END:|27-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: sugerenciasAction ">//GEN-BEGIN:|27-action|0|27-preAction
    /**
     * Performs an action assigned to the selected list element in the sugerencias component.
     */
    public void sugerenciasAction() {//GEN-END:|27-action|0|27-preAction
        // enter pre-action user code here
        String __selectedString = getSugerencias().getString(getSugerencias().getSelectedIndex());//GEN-LINE:|27-action|1|27-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|27-action|2|
    //</editor-fold>//GEN-END:|27-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: showInfo ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of showInfo component.
     * @return the initialized component instance
     */
    public Command getShowInfo() {
        if (showInfo == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            showInfo = new Command("Info", Command.OK, 0);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return showInfo;
    }
    //</editor-fold>//GEN-END:|32-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: informacion ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of informacion component.
     * @return the initialized component instance
     */
    public Form getInformacion() {
        if (informacion == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            informacion = new Form("Sabias que...", new Item[] { getImageItem1(), getStringItem3() });//GEN-BEGIN:|34-getter|1|34-postInit
            informacion.addCommand(getBackList());
            informacion.addCommand(getExitCommand());
            informacion.setCommandListener(this);//GEN-END:|34-getter|1|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return informacion;
    }
    //</editor-fold>//GEN-END:|34-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageItem1 ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initiliazed instance of imageItem1 component.
     * @return the initialized component instance
     */
    public ImageItem getImageItem1() {
        if (imageItem1 == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            imageItem1 = new ImageItem("", null, ImageItem.LAYOUT_DEFAULT, "<Missing Image>");//GEN-LINE:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return imageItem1;
    }
    //</editor-fold>//GEN-END:|35-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem3 ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initiliazed instance of stringItem3 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem3() {
        if (stringItem3 == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            stringItem3 = new StringItem("", "Aqui va la informaci\u00F3n del animalito", Item.PLAIN);//GEN-LINE:|36-getter|1|36-postInit
            // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return stringItem3;
    }
    //</editor-fold>//GEN-END:|36-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backList ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of backList component.
     * @return the initialized component instance
     */
    public Command getBackList() {
        if (backList == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            backList = new Command("Atr\u00E1s", Command.OK, 0);//GEN-LINE:|37-getter|1|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|2|
        return backList;
    }
    //</editor-fold>//GEN-END:|37-getter|2|
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: goTo ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initiliazed instance of goTo component.
     * @return the initialized component instance
     */
    public Command getGoTo() {
        if (goTo == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            goTo = new Command("Ir a...", Command.OK, 0);//GEN-LINE:|43-getter|1|43-postInit
            // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return goTo;
    }
    //</editor-fold>//GEN-END:|43-getter|2|







    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: changePos ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initiliazed instance of changePos component.
     * @return the initialized component instance
     */
    public Command getChangePos() {
        if (changePos == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            changePos = new Command("Cambiar lugar", Command.OK, 0);//GEN-LINE:|55-getter|1|55-postInit
            // write post-init user code here
        }//GEN-BEGIN:|55-getter|2|
        return changePos;
    }
    //</editor-fold>//GEN-END:|55-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|58-getter|0|58-preInit
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
            textField = new TextField("IP", "localhost", 32, TextField.ANY);//GEN-LINE:|58-getter|1|58-postInit
            // write post-init user code here
        }//GEN-BEGIN:|58-getter|2|
        return textField;
    }
    //</editor-fold>//GEN-END:|58-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField1 ">//GEN-BEGIN:|59-getter|0|59-preInit
    /**
     * Returns an initiliazed instance of textField1 component.
     * @return the initialized component instance
     */
    public TextField getTextField1() {
        if (textField1 == null) {//GEN-END:|59-getter|0|59-preInit
            // write pre-init user code here
            textField1 = new TextField("Puerto", "9090", 32, TextField.NUMERIC);//GEN-LINE:|59-getter|1|59-postInit
            // write post-init user code here
        }//GEN-BEGIN:|59-getter|2|
        return textField1;
    }
    //</editor-fold>//GEN-END:|59-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|61-getter|0|61-preInit
    /**
     * Returns an initiliazed instance of exitCommand1 component.
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|61-getter|0|61-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|61-getter|1|61-postInit
            // write post-init user code here
        }//GEN-BEGIN:|61-getter|2|
        return exitCommand1;
    }
    //</editor-fold>//GEN-END:|61-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Hastapronto ">//GEN-BEGIN:|67-getter|0|67-preInit
    /**
     * Returns an initiliazed instance of Hastapronto component.
     * @return the initialized component instance
     */
    public Form getHastapronto() {
        if (Hastapronto == null) {//GEN-END:|67-getter|0|67-preInit
            // write pre-init user code here
            Hastapronto = new Form("form1", new Item[] { getStringItem4() });//GEN-LINE:|67-getter|1|67-postInit
            // write post-init user code here
        }//GEN-BEGIN:|67-getter|2|
        return Hastapronto;
    }
    //</editor-fold>//GEN-END:|67-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem4 ">//GEN-BEGIN:|68-getter|0|68-preInit
    /**
     * Returns an initiliazed instance of stringItem4 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem4() {
        if (stringItem4 == null) {//GEN-END:|68-getter|0|68-preInit
            // write pre-init user code here
            stringItem4 = new StringItem("Gracias!! ", "Hasta pronto");//GEN-LINE:|68-getter|1|68-postInit
            // write post-init user code here
        }//GEN-BEGIN:|68-getter|2|
        return stringItem4;
    }
    //</editor-fold>//GEN-END:|68-getter|2|
    //</editor-fold>




    //</editor-fold>

    public void salir(){
         myThread = new Thread(){public void run(){
                    service.sendFix(visitados);
                    service.stopConnection();
                }};
                myThread.start();
    }



    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
        midletStopped=true;
    }

    


   

    






}
