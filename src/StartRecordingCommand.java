public class StartRecordingCommand extends Command{


  public StartRecordingCommand(Editor editor_, Application app_) {
    super(editor_, app_);
  }

  @Override
  public int execute() {
    Debug.printDebug("Starting the recording of the macro");
    m_app.startRecording();



    return 2;
  }
}
