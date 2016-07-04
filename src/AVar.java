package Example;

public class AVar{
  public Type type;
  public Object value;

  public AVar(Type t, Object v){
    this.type = t;
    this.value = v;
  }

  public AVar(Variable v){
    this.type = v.type;
    this.value = v.value;
  }

  public String toString(){
     return "Type: "+ type + " Value: " + value.toString();
 }
}
