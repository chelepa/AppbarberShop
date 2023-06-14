package br.com.barberShop.converter;

import br.com.barberShop.dto.customer.CustomerDetails;
import br.com.barberShop.entity.GroupEntity;
import br.com.barberShop.entity.UsersEntity;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UsersEntityToCustomerDetailsConverter implements Converter<UsersEntity, CustomerDetails> {

    @Override
    public CustomerDetails convert(MappingContext<UsersEntity, CustomerDetails> context) {
        CustomerDetails destination =  Objects.isNull(context.getDestination()) ? new CustomerDetails() : context.getDestination();
        destination.setLogin((context.getSource().getEmail() == null) ? destination.getLogin() : context.getSource().getEmail());
        destination.setName((context.getSource().getName().isEmpty()) ? destination.getName() : context.getSource().getName());
        destination.setPassword((context.getSource().getPassword() == null) ? destination.getPassword() : context.getSource().getPassword());
        destination.setAuthorities(getAuthorities(context.getSource().getGroup()));
        return destination;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<GroupEntity> group) {
        List<GrantedAuthority> listAuthorities = new ArrayList<>();
        group.forEach(item -> item.getPermission().forEach(authorities -> listAuthorities.add(new SimpleGrantedAuthority(authorities.getPermission()))));
        return listAuthorities;
    }
}
