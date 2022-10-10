public class OpenFileCommand extends Command{

  public OpenFileCommand(Editor editor_, Application app_) {
    super(editor_, app_);
  }

  @Override
  public int execute() {
    Debug.printDebug("Opening a file \n  (Files)->");
    m_app.switchFile();
    return 2;
  }
}
