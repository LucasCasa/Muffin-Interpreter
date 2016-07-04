package Example;

import java.util.*;

public class StmtList{
  private ArrayList<Statement> statements;

  public StmtList(){
    statements = new ArrayList<Statement>();
  }

  public void add(Statement s){
    statements.add(s);
  }

  public void run(VarList vl){
    for(Statement s: statements)
      s.execute(vl);
  }
}
