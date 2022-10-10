import java.util.ArrayList;
import java.util.Stack;
import javax.crypto.Mac;

public class MacroRecorder {
  int MAX_SIZE = 100;
  ArrayList<Command> m_tab;
  boolean m_state ;


  public MacroRecorder(){
    m_state = false;
    m_tab = new ArrayList<>();
  }

  public void setState(boolean state){
      if(state)
        m_tab.clear();
      m_state = state;
  }


  public boolean isRecording(){
    return m_state;
  }



  /**
   *
   * @param c
   * contains the state if the editor at this t moment
   * @return
   * true if the stack is not full
   */
  public boolean add(Command c){
    if(m_tab.size() < MAX_SIZE){
      m_tab.add(c);
      return true;
    }
    return false;
  }

  public void executeMacro(){
    Debug.printDebug("====Executing a Macro==== ");
    for(Command c : m_tab){
      c.execute();
    }
    Debug.printDebug("====End of the Macro ==== ");
  }


}
