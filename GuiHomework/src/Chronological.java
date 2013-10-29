import java.util.*;

public interface Chronological {
	
	boolean after(Chronological other);
	
	boolean before(Chronological other);
	
	Date getDate();
}
