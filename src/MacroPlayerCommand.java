public class MacroPlayerCommand extends Command{

  public MacroPlayerCommand(Editor editor_, Application app_) {
    super(editor_, app_);
  }

  @Override
  public int execute() {
    Debug.printDebug("executing saved macro");
    m_app.playMacro();
    return 2;
  }
}
