package Example;

import java.util.*;

public class Function{

  public String name;
  public Type type;
  public ArrayList<Param> params;
  public Body body;
  public Expression ret;

  public Function(Type t, String n,ArrayList<Param> p,Body b, Expression ret){
    this.type = t;
    this.name = n;
    this.params = p;
    this.body = b;
    this.ret = ret;

  }
  public void loadVars(ArrayList<AVar> vars){
    if(vars.size() != params.size())
      throw new IllegalArgumentException("!number of parameters");

    for(int i=0; i<params.size(); i++){
      if(params.get(i).type != vars.get(i).type){
        throw new IllegalArgumentException("Function " + name + "does not recognize this shit");
      }
      body.AddVar(new Variable(vars.get(i).type,params.get(i).name,vars.get(i).value));
    }
  }

  public AVar run(ArrayList<AVar> vl){
    loadVars(vl);
    body.run();
    return ret.execute(body.vl);
  }

}
