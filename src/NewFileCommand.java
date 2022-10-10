public class NewFileCommand extends Command{

  public NewFileCommand(Editor editor_, Application app_) {
    super(editor_, app_);
  }

  @Override
  public int execute() {
    m_app.PerformAction("save");
    m_app.newFile();
    Debug.printDebug("creating and opening a new file as '"+m_app.getCurrentFileName()+"' .");
    m_app.setNewEditortextToCurrentFile();
    m_app.refreshMenuOpen();
    return 2;
  }
}
