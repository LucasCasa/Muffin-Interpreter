package Example;

import java.util.*;
import java.util.Random;

public class ExpressionFactory{

  public static Expression integer(Integer i){
    return new Expression(){
      public AVar execute(VarList vl){
        return new AVar(Type.i,i);
      }
    };
  }

  public static Expression string(String s){
    return new Expression(){
      public AVar execute(VarList vl){
        return new AVar(Type.s,s);
      }
    };
  }

  public static Expression doubl(Double d){
    return new Expression(){
      public AVar execute(VarList vl){
        return new AVar(Type.d,d);
      }
    };
  }

  public static Expression bool(Boolean b){
    return new Expression(){
      public AVar execute(VarList vl){
        return new AVar(Type.b,b);
      }
    };
  }

  public static Expression variable(String name){
    return new Expression(){
      public AVar execute(VarList vl){
        Variable v = vl.getV(name);
        return new AVar(v.type,v.value);
      }
    };
  }

  public static Expression operation(Expression e1, Expression e2, Operator o){
    return new Expression(){
      public AVar execute(VarList vl){
        return Expr.operate(e1.execute(vl),e2.execute(vl),o);
      }
    };
  }
  public static Expression callFunc(String name, ArrayList<Expression> e){
    return new Expression(){
      public AVar execute(VarList vl){
        return SuperClass.getInstance().callFunction(name,e,vl);
      }
    };
  }

  public static Expression get(String name, Expression e){
    return new Expression(){
      public AVar execute(VarList vl){
        Variable v1 = vl.getV(name);
        AVar v = e.execute(vl);
        if(v1 == null){
          throw new IllegalArgumentException("Variable " + v1 + " has not been declared");
        }
        if(v.type != Type.i){
          throw new IllegalArgumentException("Cannot acces the array with a " + v.type + "type");
        }
        if(v1.type == Type.ai || v1.type == Type.ab || v1.type == Type.ad || v1.type == Type.as){
          return new AVar(VArray.getType(v1.type),((VArray)v1.value).getValue((Integer)v.value));
        }else{
          throw new IllegalArgumentException("Wrong type");
        }
      }
    };
  }

  public static Expression negative(Expression e){
    return new Expression(){
      public AVar execute(VarList vl){
        AVar v = e.execute(vl);
        if(v.type == Type.i || v.type == Type.d){
          return new AVar(v.type,new Integer((((Integer)v.value).intValue())*-1));
        }else if(v.type == Type.d){
          return new AVar(v.type,new Double((((Double)v.value).doubleValue())*-1));
        }else{
          throw new IllegalArgumentException("Cannot perform the operation");
        }
      }
    };
  }

  public static Expression length(String name){
    return new Expression(){
      public AVar execute(VarList vl){
        Variable v1 = vl.getV(name);
        if(v1 == null){
          throw new IllegalArgumentException("Variable " + v1 + " has not been declared");
        }
        if(v1.type == Type.ab || v1.type == Type.as || v1.type == Type.ad || v1.type == Type.ai){
          return new AVar(Type.i,((VArray)v1.value).getSize());
        }else{
          throw new IllegalArgumentException("Cannot ask length of a variable that is not an array");
        }
      }
    };
  }

  public static Expression define(Type type, String name,Expression e ){
    return new Expression(){
      public AVar execute(VarList vl){
        AVar v = e.execute(vl);
        if(v.type == Type.d && type == Type.i){
           return new Variable(type,name,new Integer(((Double)v.value).intValue()));
        }else if(type == Type.d && v.type == Type.i ){
           return new Variable(type,name,new Double(((Integer)v.value).doubleValue()));
        }else if(v.type != type){
           throw new IllegalArgumentException("Invalid assingation of variable" + name);
        }else{
           return new Variable(type,name,v.value);
        }
      }
    };
  }

  public static Expression define(Type type,String name){
    return new Expression(){
      public AVar execute(VarList vl){
        if(type == Type.as || type == Type.ab || type == Type.ad || type == Type.ai)
          return new Variable(type,name,new VArray());
        else
          throw new IllegalArgumentException("Variable " + name + "not initialized");
      }
    };
  }
  public static Expression random(Expression max){
    return new Expression(){
      public AVar execute(VarList vl){
        AVar av = max.execute(vl);
        if(av.type != Type.i)
          throw new IllegalArgumentException("Rand only accepts int");
        Random rand = new Random();
        int randomNum = rand.nextInt((Integer)(av.value));
        return new AVar(Type.i,randomNum);
      }
    };
  }
  public static Expression condition(Cond c){
    return new Expression(){
      public AVar execute(VarList vl){
        return new AVar(Type.b,c.eval(vl));
      }
    };
  }
  public static Expression compare(Expression e1, Expression e2, String op){
     return new Expression(){
        public AVar execute(VarList vl){
             AVar a1 = e1.execute(vl);
             AVar a2 = e2.execute(vl);
             if((a1.type == Type.i  || a1.type == Type.d) && (a2.type == Type.i || a2.type == Type.d)){
                 Number i1 = (Number) a1.value;
                 Number i2 = (Number) a2.value;

                 if(op == "<"){
                    return new AVar(Type.b, new Boolean(i1.doubleValue() < i2.doubleValue()));
                 }else if(op == ">"){
                    return new AVar(Type.b, new Boolean(i1.doubleValue() > i2.doubleValue()));
                 }else if(op == "<="){
                    return new AVar(Type.b, new Boolean(i1.doubleValue() <= i2.doubleValue()));
                 }else if(op == ">="){
                    return new AVar(Type.b, new Boolean(i1.doubleValue() >= i2.doubleValue()));
                 }else if(op == "=="){
                    return new AVar(Type.b, new Boolean(i1.doubleValue() == i2.doubleValue()));
                 }else if(op == "!="){
                    return new AVar(Type.b, new Boolean(i1.doubleValue() != i2.doubleValue()));
                 }else{
                    throw new IllegalArgumentException("Invalid comparation between Numbers");
                 }
             }else if(a1.type == Type.s && a2.type == Type.s){
                 String s1 = (String) a1.value;
                 String s2 = (String) a2.value;
                 if(op == "<"){
                    return new AVar(Type.b, new Boolean(s1.compareTo(s2) > 0));
                 }else if(op == ">"){
                    return new AVar(Type.b,new Boolean(s1.compareTo(s2) < 0));
                 }else if(op == "<="){
                    return new AVar(Type.b,new Boolean(s1.compareTo(s2) <= 0));
                 }else if(op == ">="){
                    return new AVar(Type.b,new Boolean(s1.compareTo(s2) >= 0));
                 }else if(op == "=="){
                    return new AVar(Type.b,new Boolean(s1.equals(s2)));
                 }else if(op == "!="){
                    return new AVar(Type.b,new Boolean(!s1.equals(s2)));
                 }else{
                    throw new IllegalArgumentException("invalid comparation between Strings");
                 }
             }
             throw new IllegalArgumentException("Illegal Type comparation");
          }
       };
    }
    public static Expression logic(Expression e1, Expression e2, String op){
       return new Expression(){
          public AVar execute(VarList vl){
             AVar a1 = e1.execute(vl);
             AVar a2 = e2.execute(vl);
             if(a1.type != Type.b && a2.type != Type.b){
                throw new IllegalArgumentException("Logic operation between not booleans");
             }
             if(op == "AND"){
               return new AVar(Type.b,new Boolean((Boolean)a1.value && (Boolean)a2.value));
            }else if(op == "OR"){
               return new AVar(Type.b,new Boolean((Boolean)a1.value || (Boolean)a2.value));
            }else{
               throw new IllegalArgumentException("Invalid operand between booleans");
            }
         }
      };
    }

    public static Expression not(Expression e1){
       return new Expression(){
          public AVar execute(VarList vl){
             AVar a1 = e1.execute(vl);
             if(a1.type != Type.b){
                throw new IllegalArgumentException("Logic operation between not booleans");
             }
             return new AVar(Type.b,!(Boolean)a1.value);
         }
      };
    }
}
