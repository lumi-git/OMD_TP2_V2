public class StateSaver {

  public void setM_posa(int m_posa) {
    this.m_posa = m_posa;
  }

  public void setM_posb(int m_posb) {
    this.m_posb = m_posb;
  }

  public void setM_poscurs(int m_poscurs) {
    this.m_poscurs = m_poscurs;
  }

  public void setM_editorText(String m_editorText) {
    this.m_editorText = m_editorText;
  }

  public int getM_posa() {
    return m_posa;
  }

  public int getM_posb() {
    return m_posb;
  }

  public int getM_poscurs() {
    return m_poscurs;
  }

  public String getM_editorText() {
    return m_editorText;
  }

  int m_posa,m_posb,m_poscurs ;
  String m_editorText;
  public StateSaver(int posa,int posb,int poscurs,String editorText){
    m_posa = posa;
    m_posb = posb;
    m_poscurs =poscurs;
    m_editorText = editorText;
  }

}
