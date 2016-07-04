package Example;

import java.util.*;
import java.io.*;

public interface BoolCond extends Cond{
  public void load(Cond c1, Cond c2, String op);
}
