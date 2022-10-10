/**
 * this class is the base of all the command in this application, permite the backup and memento design pattern to exist in this app
 */
public abstract class Command {

    protected Editor m_editor;
    protected Application m_app;

    protected StateSaver Backup ;

    public Command(Editor editor_,Application app_){
        m_editor = editor_;
        m_app = app_;
    }

    /**
     * save a state of the editor
     */
    public void saveBackup(){
        Backup = new StateSaver(m_editor.getBorneSelection_a(),m_editor.getBorneSelection_b(),m_editor.getCursorPos()
            ,m_editor.getText());

    }

    /**
     * restore a state of the editor.
     */
    public void restore(){

        m_editor.setM_text(Backup.getM_editorText());
        m_editor.setCursorPos(Backup.getM_posa());
        m_editor.setBorneSelection_a();
        m_editor.setCursorPos(Backup.getM_posb());
        m_editor.setBorneSelection_b();
        m_editor.setCursorPos(Backup.getM_poscurs());

    }

    /**
     *
     * @return
     * if return 0, it's a UndoCommand.
     * if return 1, will be saved as a command can be undo
     * if return 2, will not be able to undo this command (like opening file etc.)
     */

    public abstract int execute();

    protected Application getApplication(){
        return m_app;
    }

    protected Editor getEditor(){
        return m_editor;
    }

}
