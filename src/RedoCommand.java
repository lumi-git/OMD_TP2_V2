public class RedoCommand extends Command{

  public RedoCommand(Editor editor_, Application app_) {
    super(editor_, app_);
  }

  /**
   * note that redo is a basic command not as the undo.
   * @return
   */
  @Override
  public int execute() {
    saveBackup();
    Debug.printDebug("ReDone !");
    m_app.redo();
    return 1;
  }
}