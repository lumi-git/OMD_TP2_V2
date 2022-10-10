public class InsertCommand extends Command {

    public InsertCommand(Editor editor_, Application app_) {
        super(editor_, app_);
    }

    @Override
    public int execute() {
        saveBackup();
        Debug.printDebug("Command insert \n (insertion)-> " +m_app.getContentInInsertZone());

        m_editor.deleteSelection();

        m_editor.insertStringAt(m_editor.getBorneSelection_a(),m_app.getContentInInsertZone());

        return 1;
    }
}
