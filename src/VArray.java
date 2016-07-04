package Example;
import java.util.*;

public class VArray{
  ArrayList<Object> values;
  int size;

  public VArray(){
    this.values = new ArrayList<Object>();
    this.size = 0;
  }

  public void add(Object o){
      values.add(o);
      size ++;
  }
  public void add(Object o, Integer position){
    if(position <= size){
        values.set(position,o);
    }else{
      throw new IllegalArgumentException("Position out of range");
    }
  }

  public Object getValue(Integer position){
    if(position > size || position < 0){
      throw new IllegalArgumentException("Position out of range");
    }else{
      return values.get(position);
    }
  }

  public String toString(){
    String s = "[";
    for(int i=0; i<values.size(); i++){
      s += values.get(i).toString();
      if(i<values.size()-1)
        s+= " ";
    }
    s += "]";
    return s;
  }

  public Integer getSize(){
    return size;
  }

  public static Type getType(Type t){
    if(t == Type.ai){
      return Type.i;
    }else if(t == Type.as){
      return Type.s;
    }else if(t == Type.ad){
      return Type.d;
    }else{
      return Type.b;
    }
  }
}
