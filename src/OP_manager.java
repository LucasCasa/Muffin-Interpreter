package Example;

import java.util.*;

public class OP_manager{
  public static Map<Operator,Operation> operations = new HashMap<Operator,Operation>();

  public OP_manager(){
  	operations.put(Operator.plus_op,new Operation(){
  		public Integer operate(Integer n1, Integer n2){return n1 + n2;}
  	});
    operations.put(Operator.minus_op,new Operation(){
  		public Integer operate(Integer n1, Integer n2){return n1 - n2;}
  	});
    operations.put(Operator.times_op,new Operation(){
  		public Integer operate(Integer n1, Integer n2){return n1 * n2;}
  	});
    operations.put(Operator.mod_op,new Operation(){
  		public Integer operate(Integer n1, Integer n2){return n1 % n2;}
  	});
    operations.put(Operator.pow_op,new Operation(){
  		public Integer operate(Integer n1, Integer n2){return (int)Math.pow(n1,n2);}
  	});
    operations.put(Operator.div_op,new Operation(){
  		public Integer operate(Integer n1, Integer n2){return n1 / n2;}
  	});
  }
}
