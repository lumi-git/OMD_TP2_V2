import javax.swing.*;

public class Editor{

    int borneSelection_a,borneSelection_b = 0;
    int cursorPos = 0;
    String m_text ;


    public Editor(String text_){
        m_text = text_;

    }

    /**
     * ensure the borne is between 0 and the length of the text
     */
    public void setBorneSelection_a() {
        borneSelection_a  = Math.max(0,cursorPos);
        borneSelection_a = Math.min(m_text.length(),cursorPos);

    }


    /**
     * ensure the borne is between 0 and the length of the text
     */
    public void setBorneSelection_b() {
        borneSelection_b  = Math.max(0,cursorPos);
        borneSelection_b = Math.min(m_text.length(),cursorPos);
    }

    /**
     * refresh the position of the bornes (after a undo or a redo for example)
     */
    public void refreshBornesPos(){
        borneSelection_a = Math.min(borneSelection_a,m_text.length());
        borneSelection_b = Math.min(borneSelection_b,m_text.length());
    }

    public void setM_text(String m_text) {
        this.m_text = m_text;
    }

    public String getText() {
        return m_text;
    }

    public int getTextLen(){
        return m_text.length();
    }

    public int getBorneSelection_a() {
        return borneSelection_a;
    }

    public int getBorneSelection_b() {
        return borneSelection_b;
    }

    /**
     * ensure the cursor is always in the range of the text
     * @param cursorPos
     */
    public void setCursorPos(int cursorPos) {

        this.cursorPos = cursorPos;

        if(cursorPos > m_text.length())
            this.cursorPos = m_text.length();

        if(cursorPos < 0)
            this.cursorPos = 0;

    }

    public int getCursorPos() {
        return cursorPos;
    }


    /**
     *
     * @return
     * true if a selection exist
     */
    public boolean existSelection(){
        return borneSelection_a==borneSelection_b;
    }

    /**
     *
     * @return
     * the text between the two bornes
     */
    public String getSelection(){
        StringBuilder s = new StringBuilder();

        for(int i = borneSelection_a; i<borneSelection_b;i++){
            s.append(m_text.charAt(i));
        }


        return s.toString();
    }

    /**
     * delet the text between the two bornes
     */
    public void deleteSelection(){
        StringBuilder s1 = new StringBuilder();

        if(borneSelection_a <= borneSelection_b){
            for(int i=0 ;i<borneSelection_a;i++)
                s1.append(m_text.charAt(i));

            for(int i = borneSelection_b ;i<m_text.length();i++)
                s1.append(m_text.charAt(i));

            setCursorPos(getCursorPos()-(borneSelection_b-borneSelection_a));
            borneSelection_b =borneSelection_a;
            cursorPos =s1.length();

            setM_text(s1.toString());
        }

    }

    /**
     *
     * @param pos
     * text will be insert at this coordonate in the text
     * @param text
     * the text that will be insert
     */
    public void insertStringAt(int pos, String text){

        StringBuilder s1 = new StringBuilder();

        //on prend le début avant la pos
        for(int i=0 ;i<pos;i++){
            s1.append(m_text.charAt(i));
        }
        //on ajoute le text au niveau de la pos
        s1.append(text);


        for(int i = pos ;i<m_text.length();i++)
            s1.append(m_text.charAt(i));


        setM_text(s1.toString());
        setCursorPos(getCursorPos()+text.length());
    }
}
