public class StopRecordingCommand extends Command{


  public StopRecordingCommand(Editor editor_, Application app_) {
    super(editor_, app_);
  }

  @Override
  public int execute() {
    Debug.printDebug("Stoping the recording of the macro");
    m_app.stopRecording();
    return 2;
  }
}
