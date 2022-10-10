public class UndoCommand extends Command {


  public UndoCommand(Editor editor_, Application app_) {
    super(editor_, app_);
  }

  /**
   * undo commands are saved in a different stack
   * @return 0 to be saved in a different stack in the command history
   */
  @Override
  public int execute() {
    saveBackup();
    Debug.printDebug("unDone !");
    m_app.undo();
    return 0;
  }
}
