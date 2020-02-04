package xyz.vegaone.easytrackingv3.mapper;

import com.github.dozermapper.core.CustomConverter;
import com.github.dozermapper.core.MappingException;
import org.hibernate.collection.internal.AbstractPersistentCollection;
import org.hibernate.collection.internal.PersistentSet;
import xyz.vegaone.easytrackingv3.dto.Task;

import java.util.List;

public class SprintCustomFieldConverter implements CustomConverter {

    @Override
    public Object convert(Object destination, Object source, Class<?> destinationClass, Class<?> sourceClass) {
        if (source == null) {
            return null;
        }

        List<Task> dest;
        if ((source instanceof AbstractPersistentCollection) || ((PersistentSet) source).wasInitialized()) {
            dest = null;
            return dest;
        } else if (source instanceof List) {
            return null;
        } else {
            throw new MappingException("Converter SprintCustomFieldConverter "
                    + "used incorrectly. Arguments passed in were:"
                    + destination
                    + " and "
                    + source);
        }

//        if (!(source instanceof AbstractPersistentCollection) || ((PersistentSet) source).wasInitialized()) {
//            // Allow dozer to map as normal
//            return false;
//        } else {
//            destination = null;
//            return true;
//        }
    }
}
