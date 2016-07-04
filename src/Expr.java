package Example;

public class Expr{

  public static AVar operate(AVar v1, AVar v2, Operator o){
    if((v1.type == Type.s || v2.type == Type.s) && o != Operator.plus_op){
      throw new IllegalArgumentException("You cannot perform that operation");
    }else if(v1.type == Type.s || v2.type == Type.s){
        return new AVar(Type.s,v1.value.toString().concat(v2.value.toString()));
    }

    if(v1.type == Type.b || v2.type == Type.b){
      if(v1.type == Type.b && v2.type == Type.b){
          throw new IllegalArgumentException("Cannot perform operation between two booleans");
      }else{
          throw new IllegalArgumentException("The variables " + v1 + "and" + v2 + "are not the same type");
      }
    }
    //
    if(o == Operator.div_op && (v2.type == Type.i || v2.type == Type.d) && ((v1.type == Type.i || v1.type == Type.d))){
      if(((Number)(v2.value)).intValue()==0){
        throw new IllegalArgumentException("Cannot divide by cero");
      }
      Number n1 = (Number) v1.value;
      Number n2 = (Number) v2.value;
      return new AVar(Type.d,n1.doubleValue() / n2.doubleValue());
    }else if(o == Operator.div_op){
      throw new IllegalArgumentException("Illegal Division Types");
    }

    if(o == Operator.mod_op){
      if(v1.type != Type.i || v2.type != Type.i){
        throw new IllegalArgumentException("Cannot perform module with !int");
      }else{
        return new AVar(Type.i,(Integer)v1.value % (Integer)v2.value);
      }
    }
    if(o == Operator.plus_op){
      if((v1.type == Type.d  || v1.type == Type.i) && (v2.type == Type.d || v2.type == Type.i)){
        if(v1.type == Type.d && v2.type == Type.d){
          return new AVar(Type.d,(Double)(v1.value) + (Double)(v2.value));
        }else if(v1.type == Type.d){
          return new AVar(Type.d,(Double)(v1.value) + (Integer)(v2.value));
        }else if(v2.type == Type.d){
          return new AVar(Type.d,(Integer)(v1.value) + (Double)(v2.value));
       }else if(v1.type == Type.i && v2.type == Type.i){
          return new AVar(Type.i,(Integer)(v1.value) + (Integer)(v2.value));
        }
      }
    }

    if(o == Operator.minus_op){
      if((v1.type == Type.d  || v1.type == Type.i) && (v2.type == Type.d || v2.type == Type.i)){
        if(v1.type == Type.d && v2.type == Type.d){
          return new AVar(Type.d,(Double)(v1.value) - (Double)(v2.value));
        }else if(v1.type == Type.d){
          return new AVar(Type.d,(Double)(v1.value) - (Integer)(v2.value));
        }else if(v2.type == Type.d){
          return new AVar(Type.d,(Integer)(v1.value) - (Double)(v2.value));
        }else{
          return new AVar(Type.i,(Integer)(v1.value) - (Integer)(v2.value));
        }
      }
    }

    if(o == Operator.times_op){
      if((v1.type == Type.d  || v1.type == Type.i) && (v2.type == Type.d || v2.type == Type.i)){
        if(v1.type == Type.d && v2.type == Type.d){
          return new AVar(Type.d,(Double)(v1.value) * (Double)(v2.value));
        }else if(v1.type == Type.d){
          return new AVar(Type.d,(Double)(v1.value) * (Integer)(v2.value));
        }else if(v2.type == Type.d){
          return new AVar(Type.d,(Integer)(v1.value) * (Double)(v2.value));
        }else{
          return new AVar(Type.i,(Integer)(v1.value) * (Integer)(v2.value));
        }
      }
    }
    if(o == Operator.pow_op){
      if((v1.type == Type.d  || v1.type == Type.i) && (v2.type == Type.d || v2.type == Type.i)){
         Number i1 = (Number) v1.value;
         Number i2 = (Number) v2.value;
         return new AVar(Type.d,Math.pow(i1.doubleValue(),i2.doubleValue()));
      }
    }
      throw new IllegalArgumentException("Invalid Operator wachin");
  }

}
