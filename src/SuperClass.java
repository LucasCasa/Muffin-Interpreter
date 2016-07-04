package Example;

import java.util.*;

public class SuperClass {
  private static SuperClass sc = new SuperClass();
  HashMap<String,Function> funcs;
  private SuperClass(){
    funcs = new HashMap<String,Function>();
  }
  public static SuperClass getInstance(){
    return sc;
  }

  public void add(Function f){
    if(funcs.containsKey(f.name)){
      throw new IllegalArgumentException("Function already exists: " + f.name);
    }
    funcs.put(f.name,f);
  }
  public int start(){
    Function f = funcs.get("muffin");
    if(f == null){
      throw new IllegalArgumentException("NO MUFFIN");
    }else{
      f.run(new ArrayList<AVar>());
      return 0;
    }
  }

  public AVar callFunction(String name, ArrayList<Expression> e,VarList vl){
      ArrayList<AVar> v = new ArrayList<AVar>();
      for(Expression ex : e){
        v.add(ex.execute(vl));
      }
      return funcs.get(name).run(v);

  }
}
