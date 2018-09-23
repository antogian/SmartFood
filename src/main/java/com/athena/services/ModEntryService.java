package com.athena.services;

import com.athena.entities.ModEntry;
import com.athena.entities.Modifier;
import com.athena.model.ModEntryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModEntryService
{
    public List<ModEntryDTO> getModEntries(Modifier modifier, int[] inclusions)
    {
        List<ModEntryDTO> allEntries = new ArrayList<>();

        for(ModEntry modEntry : modifier.getEntries())
        {
            ModEntryDTO entryDto = new ModEntryDTO();

            entryDto.setName(modEntry.getName());
            entryDto.setIndex(modEntry.getIndex());

            entryDto.setIncluded(getInclusion(entryDto.getIndex(), inclusions));

            if(entryDto.isIncluded())
                entryDto.setCost(new double[5]);
            else
            {
                double[] cost = new double[5];
                for(int i=0; i<modEntry.getCost().length; i++)
                {
                    cost[i] = modEntry.getCost()[i];
                }
                entryDto.setCost(cost);
            }

            if(modifier.isHalfEnabled())
            {
                if(entryDto.isIncluded())
                    entryDto.setHalfCost(new double[5]);
                else
                {
                    double[] halfCost = new double[5];
                    for(int i=0; i<modEntry.getHalfCost().length; i++)
                    {
                        halfCost[i] = modEntry.getHalfCost()[i];
                    }
                    entryDto.setHalfCost(halfCost);
                }
            }

            allEntries.add(entryDto);
        }

        return allEntries;
    }

    private boolean getInclusion(int index, int[] inclusions)
    {
        for(int i=0; i<inclusions.length; i++)
        {
            if(inclusions[i] == index)
            {
                return true;
            }
        }
        return false;
    }

    public ModEntryDTO getValues(ModEntryDTO modEntry, boolean isHalfEnabled)
    {
        ModEntryDTO newEntry = new ModEntryDTO();
        newEntry.setName(modEntry.getName());

        double[] cost = new double[5];
        for(int i=0; i<modEntry.getCost().length; i++)
        {
            cost[i] = modEntry.getCost()[i];
        }
        newEntry.setCost(cost);

        if(isHalfEnabled)
        {
            double[] halfCost = new double[5];
            for(int i=0; i<modEntry.getHalfCost().length; i++)
            {
                halfCost[i] = modEntry.getHalfCost()[i];
            }
            newEntry.setHalfCost(halfCost);
        }

        newEntry.setIndex(modEntry.getIndex());
        newEntry.setSelected(modEntry.isSelected());

        return newEntry;
    }
}
