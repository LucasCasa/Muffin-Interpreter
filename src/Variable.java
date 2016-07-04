package Example;

import java.lang.*;

public class Variable extends AVar{
    public String name;

    public Variable(Type t, String n, Object v){
      super(t,v);
      this.name = n;
    }

    public Variable(Type t, String n, VArray v){
      super(t,v);
      this.name = n;
    }

    public String getString(){
      if(type != Type.s)
        throw new IllegalArgumentException("Object is not a String");
      return (String)value;
    }

    public Integer getInteger(){
      if(type != Type.i)
        throw new IllegalArgumentException("Object is not an Integer");
      return (Integer)value;
    }

    public Double getDouble(){
      if(type != Type.d)
        throw new IllegalArgumentException("Object is not a Double");
      return (Double)value;
    }

    public Boolean getBoolean(){
      if(type != Type.b)
        throw new IllegalArgumentException("Object is not a Boolean");
      return (Boolean)value;
    }

    public int hashCode(){
      return name.hashCode();
    }

    public boolean equals(Object other){
      return name.equals(((Variable)other).name);
    }

    public String toString(){
      return "Var= " + name + " - Type= " + type + " - Value= " + value;
    }
}
