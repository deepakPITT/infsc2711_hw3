package Recommendation;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Compare implements Comparator<Long> { 

  Map<Long, List> map1;
  
  public Compare(Map<Long, List> base) {
      this.map1 = map1;
  }
  
  public int compare(Long a, Long b) {
      if (map1.get(a).size() >map1.get(b).size()) {
          return -1;
      } 
      if(map1.get(a).size() < map1.get(b).size()) {
          return 1;
      } 
      return a.compareTo(b);
}
}
