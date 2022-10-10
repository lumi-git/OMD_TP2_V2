import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.*;
import jdk.javadoc.doclet.Taglet.Location;

/**
 * application class is the masterClass of the program.
 * this class is the window from which will come the shortcuts and activators like buttons etc.
 * it can performe action on file and send the command based on a activator.
 */
public class Application extends JFrame implements ActionListener {
    private String fileName;

    JTextArea TextZone;
    JLabel LBL_Bornea_Pos;
    JLabel LBL_Borneb_Pos;
    Editor m_edit;
    String ClipBoard;

    JTextArea insertZone;

    JLabel charCount;

    JMenuItem mnuStopRecording;

    JMenu mnuFile;

    JMenu mnuOpen;

    CommandHistory commandHistory;

    MacroRecorder macroRecorder;

    public Application() {

        System.out.println("| General Consol Debug |");
        System.out.println("-> TP2 OMD V2 ");

        fileName = "textFiles/Default";
        ClipBoard = "";
        macroRecorder = new MacroRecorder();
        m_edit = new Editor(LoadFileToString(fileName));
        commandHistory = new CommandHistory();
    }

    public String getCurrentFileName(){
        return fileName;
    }

    public void setNewEditortextToCurrentFile(){
        m_edit.setM_text(LoadFileToString(fileName));
        TextZone.setText(m_edit.getText());
    }


    public String LoadFileToString(String fileName_) {

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                new FileReader(fileName_);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);
            StringBuilder s = new StringBuilder();
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                s.append(line+"\n");
            }

            // Always close files.
            bufferedReader.close();
            return s.toString();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +
                    fileName_ + "'");
            return "Unable to open file '" +
                fileName_ + "'";
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                    + fileName_ + "'");
            return "Error reading file '"
                + fileName_ + "'";
        }
    }

    public void newFile(){
        fileName = "textFiles/newtextFile_"+CountFile();
        try{

            // Writing into the file
            Files.writeString(Path.of(fileName),"");

        }catch(Exception e){
            Debug.printDebug("can't write in file");
        }

    }

    public void SaveFile(){
        try{

            // Writing into the file
            Files.writeString(Path.of(fileName),TextZone.getText());
            m_edit.setM_text(TextZone.getText());

        }catch(Exception e){
            Debug.printDebug("can't write in file");
        }

    }


    public void setClipBoard(String text) {
        ClipBoard = text;
    }

    public String getClipBoard() {
        return ClipBoard;
    }


    /**
     * creation de l'UI
     */
    public void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        setTitle("Micro(not)soft Word V2");

        setJMenuBar(createMenuBar());



        charCount = new JLabel();

        charCount.setBounds(800, 50,300 , 10);
        charCount.setText("Characters : 0");
        add(charCount);

        TextZone = new JTextArea();
        TextZone.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {

                m_edit.setCursorPos(TextZone.getCaretPosition());
                charCount.setText("Characters : "+TextZone.getText().length());

            }
        });

        TextZone.setBounds(10, 10, 500, 300);

        JScrollPane sp = new JScrollPane(TextZone);
        sp.setBounds(10, 10, 700, 500);
        add(sp);

        insertZone = new JTextArea();
        insertZone.setBounds(750, 300, 200, 50);

        JScrollPane sp2 = new JScrollPane(insertZone);
        sp2.setBounds(750, 300, 200, 220);
        add(sp2);

        JButton buttonBa = new JButton("set Borne a");
        buttonBa.setBounds(750, 100, 200, 40);
        buttonBa.addActionListener(this);
        buttonBa.setActionCommand("Bornea");
        add(buttonBa);

        JButton buttonBb = new JButton("set Borne b");
        buttonBb.setBounds(750, 180, 200, 40);
        buttonBb.addActionListener(this);
        buttonBb.setActionCommand("Borneb");
        add(buttonBb);

        JButton button1 = new JButton("Insert");
        button1.setBounds(750, 260, 200, 40);
        button1.addActionListener(this);
        button1.setActionCommand("insert");
        add(button1);

        /*
        JButton button1 = new JButton("copy");
        button1.setBounds(900, 100, 100, 40);
        button1.addActionListener(this);
        button1.setActionCommand("copy");
        add(button1);

        JButton button2 = new JButton("paste");

        button2.setBounds(900, 180, 100, 40);
        button2.addActionListener(this);
        button2.setActionCommand("paste");
        add(button2);

        JButton button3 = new JButton("cut");

        button3.setBounds(900, 260, 100, 40);
        button3.addActionListener(this);
        button3.setActionCommand("cut");
        add(button3);


        //UndoButton
        JButton buttonUndo = new JButton("Undo");
        buttonUndo.setBounds(700, 500, 200, 40);
        buttonUndo.addActionListener(this);
        buttonUndo.setActionCommand("undo");
        add(buttonUndo);

        JButton buttonRedo = new JButton("Redo");
        buttonRedo.setBounds(700, 450, 200, 40);
        buttonRedo.addActionListener(this);
        buttonRedo.setActionCommand("redo");
        add(buttonRedo);
        */



        //label for bornea
        LBL_Bornea_Pos = new JLabel();
        LBL_Bornea_Pos.setBounds(750, 150, 50, 10);
        add(LBL_Bornea_Pos);

        //label for borneb
        LBL_Borneb_Pos = new JLabel();
        LBL_Borneb_Pos.setBounds(750, 240, 50, 10);
        add(LBL_Borneb_Pos);

        setLayout(null);
        setVisible(true);

        //refresh methode for the UI
        refreshTextContent();
        charCount.setText("Characters : "+TextZone.getText().length());
    }
    public void mnuNewListener( ActionEvent event ) {
        JOptionPane.showMessageDialog( this, "Button clicked !" );
    }

    /**
     *
     * Methode de construction de la barre de menu
     *
     **/
    private JMenuBar createMenuBar() {

        // La barre de menu à proprement parler
        JMenuBar menuBar = new JMenuBar();

        // Définition du menu déroulant "File" et de son contenu
        mnuFile = new JMenu( "File" );
        mnuFile.setMnemonic( 'F' );

        JMenuItem mnuNewFile = new JMenuItem( "New File" );
        mnuNewFile.setActionCommand("newFile");
        mnuNewFile.addActionListener(this);
        mnuFile.add(mnuNewFile);

        mnuFile.addSeparator();


        JMenuItem mnuSaveFile = new JMenuItem( "Save File ..." );
        mnuSaveFile.setActionCommand("save");
        mnuSaveFile.addActionListener(this);
        mnuFile.add(mnuSaveFile);
        /*
        JMenuItem mnuSaveFileAs = new JMenuItem( "Save File As ..." );
        mnuSaveFileAs.setActionCommand("saveAs");
        mnuSaveFileAs.addActionListener(this);
        mnuFile.add(mnuSaveFileAs);
        */
        mnuFile.addSeparator();

        JMenuItem mnuExit = new JMenuItem( "Exit" );
        mnuExit.setActionCommand("exit");
        mnuExit.addActionListener(this);
        mnuFile.add(mnuExit);
        mnuFile.addSeparator();
        menuBar.add(mnuFile);

        // Définition du menu déroulant "Edit" et de son contenu
        JMenu mnuEdit = new JMenu( "Edit" );
        mnuEdit.setMnemonic( 'E' );

        JMenuItem mnuUndo = new JMenuItem( "Undo" );
        mnuUndo.addActionListener(this);
        mnuUndo.setActionCommand("undo");
        mnuEdit.add(mnuUndo);

        JMenuItem mnuRedo = new JMenuItem( "Redo" );
        mnuRedo.addActionListener(this);
        mnuRedo.setActionCommand("redo");
        mnuEdit.add(mnuRedo);

        mnuEdit.addSeparator();

        JMenuItem mnuCopy = new JMenuItem( "Copy" );
        mnuCopy.addActionListener(this);
        mnuCopy.setActionCommand("copy");
        mnuEdit.add(mnuCopy);

        JMenuItem mnuCut = new JMenuItem( "Cut" );
        mnuCut.addActionListener(this);
        mnuCut.setActionCommand("cut");
        mnuEdit.add(mnuCut);

        JMenuItem mnuPaste = new JMenuItem( "Paste" );
        mnuPaste.addActionListener(this);
        mnuPaste.setActionCommand("paste");
        mnuEdit.add(mnuPaste);

        menuBar.add(mnuEdit);

        // Définition du menu déroulant "Help" et de son contenu
        JMenu mnuHelp = new JMenu( "Help" );
        mnuHelp.setMnemonic( 'H' );

        JMenuItem mnuGetHelp = new JMenuItem("Help");
        mnuGetHelp.addActionListener(this);
        mnuGetHelp.setActionCommand("help");
        mnuHelp.add(mnuGetHelp);

        menuBar.add( mnuHelp );


        // Définition du menu déroulant "macro" et son contenu
        JMenu mnuMacro = new JMenu( "Macro" );
        mnuHelp.setMnemonic( 'M' );

        JMenuItem mnuStartRecording = new JMenuItem("Start Recording");
        mnuStartRecording.addActionListener(this);
        mnuStartRecording.setActionCommand("startRecording");
        mnuMacro.add(mnuStartRecording);

        mnuStopRecording = new JMenuItem("Stop Recording");
        mnuStopRecording.addActionListener(this);
        mnuStopRecording.setActionCommand("stopRecording");
        mnuMacro.add(mnuStopRecording);
        mnuStopRecording.setVisible(false);

        JMenuItem mnuPlayMacro = new JMenuItem("Play Macro");
        mnuPlayMacro.addActionListener(this);
        mnuPlayMacro.setActionCommand("playMacro");
        mnuMacro.add(mnuPlayMacro);

        menuBar.add( mnuMacro );
        mnuOpen = new JMenu("Open File");
        refreshMenuOpen();
        menuBar.add(mnuOpen);

        return menuBar;
    }

    /**
     * undo will take the latest command in the general command stack,and restore the state of the editor before the command
     */
    public void undo(){
        Command c = commandHistory.getLast();
        if(c != null){
            c.restore();
        }
    }

    /**
     * redo will take the latest command in the undo stack and restore the state of the editor before the undo
     */
    public void redo(){
        Command c = commandHistory.getLastUndo();
        if( c!= null){
            c.restore();
        }
    }

    /**
     * Starting a recording session for the macros
     */
    public void startRecording(){
        macroRecorder.setState(true);
        mnuStopRecording.setVisible(true);

    }

    /**
     * stop the recording session
     */
    public void stopRecording(){
        macroRecorder.setState(false);
        mnuStopRecording.setVisible(false);
    }

    public void playMacro(){
        macroRecorder.executeMacro();
    }


    /**
     * all the command will be executed from here
     * @param c
     * the command to be executed
     */
    public void execute(Command c) {
        int res = c.execute();

        if(macroRecorder.isRecording() && res != 2){
            macroRecorder.add(c);
        }

        if(res == 1) {
            commandHistory.add(c);
        }
        if(res == 0){
            commandHistory.addUndo(c);
        }

    }


    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //TextZone.grabFocus();
        PerformAction(e.getActionCommand());
    }

    public void refreshMenuOpen(){
        mnuOpen.removeAll();
        for(String s :getFilesName()){
            JMenuItem open = new JMenuItem("Open file '"+s+"'");
            open.addActionListener(this);
            open.setActionCommand("OPENtextFiles/"+s);
            mnuOpen.add(open);
        }

    }

    public void PerformAction(String action){


        if(action.startsWith("OPEN")){
            execute(new OpenFileCommand(m_edit,this));
            StringBuilder fileName_ = new StringBuilder();
            for(int i = 4;i<action.length();i++ ){
                fileName_.append(action.toCharArray()[i]);
            }

            execute(new SaveCommand(m_edit,this));
            fileName = fileName_.toString();
            Debug.printDebug("Opening '"+fileName_.toString()+"'");
            setNewEditortextToCurrentFile();

        }

        switch (action) {
            case "copy" -> {m_edit.setM_text(TextZone.getText());
                execute(new CopyCommand(m_edit, this));
            }
            case "cut" -> {m_edit.setM_text(TextZone.getText());
                execute(new CutCommand(m_edit, this));
            }
            case "paste" -> {m_edit.setM_text(TextZone.getText());
                execute(new PastCommand(m_edit, this));
            }
            case "insert" -> {
                m_edit.setM_text(TextZone.getText());
                execute(new InsertCommand(m_edit, this));
            }
            case "Bornea" -> {m_edit.setM_text(TextZone.getText());
                m_edit.setBorneSelection_a();
            }
            case "Borneb" -> {m_edit.setM_text(TextZone.getText());
                m_edit.setBorneSelection_b();
            }
            //edit commands
            case "undo" -> {execute(new UndoCommand(m_edit,this));}
            case "redo" ->{execute(new RedoCommand(m_edit,this));}
            case "exit" -> System.exit(0);
            case "save" -> execute(new SaveCommand(m_edit,this));
            case "startRecording" -> execute(new StartRecordingCommand(m_edit,this));
            case "stopRecording" -> execute(new StopRecordingCommand(m_edit,this));
            case "playMacro" -> {m_edit.setM_text(TextZone.getText());
                execute(new MacroPlayerCommand(m_edit,this));}
            //utilities commands
            case "newFile" ->execute(new NewFileCommand(m_edit,this));
            case "help" -> execute(new HelpCommand(m_edit,this));
        }
        refreshTextContent();

    }



    public void showSelection() {
        m_edit.setM_text(TextZone.getText());
        TextZone.select(m_edit.getBorneSelection_a(), m_edit.getBorneSelection_b());
    }

    /**
     * switch the file and open it
     */
    public void switchFile(){
    }


    /**
     * refresh the content of the textZone in the window
     */
    public void refreshTextContent() {

        TextZone.setText(m_edit.getText());
        m_edit.refreshBornesPos();
        LBL_Bornea_Pos.setText(Integer.toString(m_edit.getBorneSelection_a()));
        LBL_Borneb_Pos.setText(Integer.toString(m_edit.getBorneSelection_b()));

    }

    public String getContentInInsertZone(){
        return insertZone.getText();
    }


    /**
     * util function to be sur to not overwrite a file when creating a new.
     * @return the number of file in the textFile folder
     */
    public static int  CountFile(){
        File directory=new File("textFiles");
        try{
            return directory.list().length;
        }catch(Exception e){
            return 1;
        }

    }

    public static String[]  getFilesName(){
        File directory=new File("textFiles");
        return directory.list();

    }



    public void sendHelpDialogBox(){

        JDialog d = new JDialog(this,"Help");


        d.setLocation(200,200);
        d.setSize(400,400);

        JTextArea l = new JTextArea();

        l.setBounds(0,0,400,400);
        JScrollPane sp = new JScrollPane();
        sp.setBounds(0,0,400,400);

        sp.add(l);
        d.add(sp);
        l.setText("Read the readme");
        d.setVisible(true);

    }
}
