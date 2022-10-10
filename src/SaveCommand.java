public class SaveCommand extends Command{

  public SaveCommand(Editor editor_, Application app_) {
    super(editor_, app_);
  }

  @Override
  public int execute() {
    Debug.printDebug("File '" + m_app.getCurrentFileName()+"' Saved.");
    m_app.SaveFile();
    return 2;
  }
}
