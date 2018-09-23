package com.athena.services;

import com.athena.entities.Size;
import com.athena.model.SizeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeService
{
    public List<SizeDTO> getSizes(Size size)
    {
        List<SizeDTO> allSizes = new ArrayList<>();

        if (size.getNames() == null || size.getNames().isEmpty())
        {
            return  allSizes;
        }
        int index = 1;
        for(String name : size.getNames())
        {
            SizeDTO newSize = new SizeDTO();
            newSize.setIndex(index);
            newSize.setName(name);
            allSizes.add(newSize);
            index++;
        }

        return allSizes;
    }
}
