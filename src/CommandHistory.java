import java.util.Stack;

public class CommandHistory {

  //because we take care of the memory, let's make the history not infinite.
  int MAX_SIZE = 100;
  Stack<Command> m_pile;
  Stack<Command> m_pileUndo;


  //used later for the recorder.





  public CommandHistory(){
    m_pile = new Stack<>();
    m_pileUndo = new Stack<>();
  }


  /**
   *
   * @param c
   * contains the state if the editor at this t moment
   * @return
   * true if the stack is not full
   */
  public boolean add(Command c){
    if(m_pile.size() < MAX_SIZE){
      m_pile.add(c);
      return true;
    }
    return false;


  }

  /**
   * get the last command beeing performed
   * @return
   */
  public Command getLast(){
    if(!m_pile.empty()){
      return m_pile.pop();
    }
    return null;
  }

  /**
   *
   * @param c
   * c contains the state of the editor at a t moment.
   * it will be saved in a pile, to be unpiled if a undo is executed
   * @return
   * true if the limit has not been reached
   */
  public boolean addUndo(Command c){
    if(m_pileUndo.size() < MAX_SIZE){
      m_pileUndo.add(c);
      return true;
    }
    return false;
  }

  /**
   *
   * @return
   * the last UndoCommand beeing performed
   */
  public Command getLastUndo(){
    if(!m_pileUndo.empty()){
      return m_pileUndo.pop();
    }
    return null;
  }


}