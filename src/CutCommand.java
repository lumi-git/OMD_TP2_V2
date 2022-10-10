public class CutCommand extends Command {
    public CutCommand(Editor editor_, Application app_) {
        super(editor_, app_);
    }

    @Override
    public int execute() {
        saveBackup();
        Debug.printDebug("Command cut \n (selection)->"+m_editor.getSelection());
        m_app.setClipBoard(m_editor.getSelection());
        m_editor.deleteSelection();

        return 1;
    }
}
