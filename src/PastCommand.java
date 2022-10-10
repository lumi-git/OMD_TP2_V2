public class PastCommand extends Command{
    public PastCommand(Editor editor_, Application app_) {
        super(editor_, app_);
    }

    @Override
    public int execute(){
        saveBackup();
        Debug.printDebug("Command paste \n (text)->"+m_app.getClipBoard());

        m_editor.insertStringAt(m_editor.getCursorPos(),m_app.getClipBoard() );
        return 1;
    }


}
