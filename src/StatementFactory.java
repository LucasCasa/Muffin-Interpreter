package Example;

import java.util.Scanner;

public class StatementFactory{


  public static Statement empty(){
    return new Statement(){
      public void execute(VarList vl){};
    };
  }

  public static Statement exec(Expression exp){
    return new Statement(){
      public void execute(VarList vl){
         exp.execute(vl);
     }
    };
  }

  public static Statement assign(String s, Expression exp){
    return new Statement(){
      public void execute(VarList vl){
        Variable v1 = vl.getV(s);
        AVar av = exp.execute(vl);
        if(v1 == null){
          throw new IllegalArgumentException("Variable " + s + " has not been declared");
       }
       if(v1.type == Type.i && av.type == Type.d){
         vl.add(new Variable(v1.type,s,new Integer(((Double)av.value).intValue())));
      }else if(v1.type == Type.d && av.type == Type.i){
         vl.add(new Variable(v1.type,s,new Double(((Integer)av.value).doubleValue())));
      }else{
        if(v1.type != av.type){
          throw new IllegalArgumentException("Type " + v1.type + " cannot be cast to " + av.type);
        }
        vl.add(new Variable(v1.type,s,av.value));
     }
     }
    };
  }
  public static Statement assignArray(String s,Expression exp){
    return new Statement(){
      public void execute(VarList vl){
        Variable v1 = vl.getV(s);
        AVar av = exp.execute(vl);
        if(v1 == null){
          throw new IllegalArgumentException("Variable " + s + " has not been declared");
       }
       if((v1.type == Type.ab && av.type == Type.b) || (v1.type == Type.as && av.type == Type.s) || (v1.type == Type.ad && av.type == Type.d) || (v1.type == Type.ai && av.type == Type.i)){
         ((VArray)v1.value).add(av.value);
         vl.add(new Variable(v1.type,s,((VArray)v1.value)));
       }else if(v1.type == Type.ad && av.type == Type.i){
         ((VArray)v1.value).add(((Integer)av.value).doubleValue());
         vl.add(new Variable (v1.type,s,((VArray)v1.value)));
       }else if(v1.type == Type.ai && av.type == Type.d){
         ((VArray)v1.value).add(((Double)av.value).intValue());
         vl.add(new Variable (v1.type,s,(VArray)v1.value));
       }else{
         throw new IllegalArgumentException("Type " + v1.type + " cannot be cast to " + av.type);
       }
        }
    };
  }


  public static Statement assign(String s,Expression e1, Expression e2){
    return new Statement(){
      public void execute(VarList vl){
        Variable v1 = vl.getV(s);
        AVar av = e2.execute(vl);
        AVar av2 = e1.execute(vl);
        if(v1 == null){
          throw new IllegalArgumentException("Variable " + s + " has not been declared");
       }
       if(av2.type != Type.i){
         throw new IllegalArgumentException("Cannot acces " + v1+ "with type" + av2.type);
       }
       if((v1.type == Type.ab && av.type == Type.b) || (v1.type == Type.as && av.type == Type.s) || (v1.type == Type.ad && av.type == Type.d) || (v1.type == Type.ai && av.type == Type.i)){
         ((VArray)v1.value).add(av.value,(Integer)av2.value);
         vl.add(new Variable(v1.type,s,((VArray)v1.value)));
       }else if(v1.type == Type.ad && av.type == Type.i){
         ((VArray)v1.value).add(((Integer)av.value).doubleValue(),(Integer)av2.value);
         vl.add(new Variable (v1.type,s,((VArray)v1.value)));
       }else if(v1.type == Type.ai && av.type == Type.d){
         ((VArray)v1.value).add(((Double)av.value).intValue(),(Integer)av2.value);
         vl.add(new Variable (v1.type,s,(VArray)v1.value));
       }else{
         throw new IllegalArgumentException("Type " + v1.type + " cannot be cast to " + av.type);
       }
        }
    };
  }

  public static Statement print(Expression e){
    return new Statement(){
      public void execute(VarList vl){
        System.out.print(e.execute(vl).value);
        System.out.print("\n");
      };
    };
  }

  public static Statement read(String name){
    return new Statement(){
      public void execute(VarList vl){
        Variable v = vl.getV(name);
        Scanner reader = new Scanner(System.in);
        switch(v.type){
          case i:  v.value = reader.nextInt();
                        break;
          case s:  v.value = reader.nextLine();
                        break;
          case d:  v.value = reader.nextDouble();
                        break;
        }
      };
    };
  }

  public static Statement ifThen(Expression c, StmtList sl){
    return new Statement(){
      public void execute(VarList vl){
         AVar a = c.execute(vl);
         if(a.type != Type.b){
            throw new IllegalArgumentException("If condition is not boolean");
         }
        if((Boolean)a.value){
          sl.run(vl);
        }
      };
    };
  }
  public static Statement ifElse(Expression c, StmtList sl1, StmtList sl2){
    return new Statement(){
      public void execute(VarList vl){
         AVar a = c.execute(vl);
         if(a.type != Type.b){
            throw new IllegalArgumentException("If condition is not boolean");
         }
       if((Boolean)a.value){
          sl1.run(vl);
        }else{
          sl2.run(vl);
        }
      };
    };
  }
  public static Statement doWhile(Expression c, StmtList sl1){
    return new Statement(){
      public void execute(VarList vl){
        while((Boolean)c.execute(vl).value){
          sl1.run(vl);
        }
      };
    };
  }
}
