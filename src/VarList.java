package Example;

import java.util.*;

public class VarList{
  private HashSet<Variable> values;
  private ArrayList<Expression> exp;
  public VarList(){
    values = new HashSet<Variable>();
    exp = new ArrayList<Expression>();
  }

  public void add(Variable d){
    boolean b = values.remove(d);
    values.add(d);
  }
  public void add(Expression e){
    exp.add(e);
  }
  public void execute(){
    for(Expression e : exp){
      Variable v = (Variable) e.execute(this);
      values.remove(v);
      values.add(v);
    }
  }
  public Variable getV(String name){
    for(Variable d: values){
      if(name.equals(d.name))
        return d;
    }
    return null;
  }

  public int size(){
    return values.size();
  }

  public String toString(){
    String s = "[";
    for(Variable v: values){
      s += v.toString();
      s += " | ";
    }
    s += "]";
    return s;
  }
}
