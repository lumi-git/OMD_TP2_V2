public class HelpCommand extends Command{

  public HelpCommand(Editor editor_, Application app_) {
    super(editor_, app_);
  }

  @Override
  public int execute() {
    m_app.sendHelpDialogBox();
    return 2;
  }
}
