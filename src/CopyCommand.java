public class CopyCommand extends Command {

    public CopyCommand(Editor editor_, Application app_) {
        super(editor_, app_);
    }

    @Override
    public int execute() {
        Debug.printDebug("Command copy \n (selection)->"+m_editor.getSelection());

        m_app.setClipBoard(m_editor.getSelection());

        return 2;
    }
}
//mabilay
