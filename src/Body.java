package Example;


public class Body{
      public VarList vl;
      public StmtList sl;

    public Body(VarList vl, StmtList sl){
      this.vl = vl;
      this.sl = sl;
      //vl.execute();
    }

    public void AddVar(Variable v){
      vl.add(v);
    }

    public void run(){
      vl.execute();
      sl.run(vl);
    }
}
