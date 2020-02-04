package xyz.vegaone.easytrackingv3.mapper;

import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import xyz.vegaone.easytrackingv3.domain.UserEntity;
import xyz.vegaone.easytrackingv3.dto.User;

public class EasytrackingBeanMappingBuilder extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(User.class, UserEntity.class)
                .fields("tasks", "tasks", FieldsMappingOptions.customConverter(SprintCustomFieldConverter.class));
    }
}
