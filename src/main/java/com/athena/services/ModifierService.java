package com.athena.services;

import com.athena.entities.Item;
import com.athena.entities.Modifier;
import com.athena.model.ItemDTO;
import com.athena.model.ModEntryDTO;
import com.athena.model.ModifierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModifierService
{
    @Autowired
    ModEntryService modEntryService; //TODO: Bad practice.

    public ModifierService()
    {

    }

    public List<ModifierDTO> getModifiers(Item item)
    {
        List<ModifierDTO> allModifiers = new ArrayList<>();
        for(int i=0; i<item.getModifiers().size(); i++)
        {
            ModifierDTO modifierDto = new ModifierDTO();

            modifierDto.setHalfEnabled(item.getModifiers().get(i).isHalfEnabled());
            modifierDto.setName(item.getModifiers().get(i).getName());
            modifierDto.setPlatter(item.getModifiers().get(i).isPlatter());
            modifierDto.setPlatterPrice(item.getModifiers().get(i).getPlatterPrice());
            modifierDto.setQualifiersEnabled(item.getModifiers().get(i).isQualifiersEnabled());

            List<ModEntryDTO> allEntries = new ArrayList<>();
            if(item.getModifiers().get(i).getEntries() == null || item.getModifiers().get(i).getEntries().isEmpty())
            {
                modifierDto.setEntries(allEntries);
            }
            else
            {
                allEntries = modEntryService.getModEntries(item.getModifiers().get(i), item.getInclusions()[i]);
                modifierDto.setEntries(allEntries);
            }

            allModifiers.add(modifierDto);
        }
        return allModifiers;
    }

    public ModifierDTO getValues(ModifierDTO mod)
    {
        ModifierDTO newModifier = new ModifierDTO();

        newModifier.setName(mod.getName());
        newModifier.setHalfEnabled(mod.isHalfEnabled());
        newModifier.setPlatter(mod.isPlatter());
        newModifier.setPlatterPrice(mod.getPlatterPrice());
        newModifier.setQualifiersEnabled(mod.isQualifiersEnabled());

        List<ModEntryDTO> allEntries = new ArrayList<>();
        for(ModEntryDTO entryDto : mod.getEntries())
        {
            allEntries.add(modEntryService.getValues(entryDto));
        }
        newModifier.setEntries(allEntries);

        return newModifier;
    }
}
