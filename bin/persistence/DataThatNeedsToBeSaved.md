# model.characters.Creature
   - inventory
        - Not necessarily the inventory itself. More, amount of 
          batwings in the inventory. Import ui.Inventory?
   - attackCooldownTime
   - attackCooldown
   - abilityToAttack
   - x, y coordinates

# model.items.Batwings
   - amount of batwings (probably get this information from the creature class?)

# model.rooms.Cave
   - width (constant? so no need)
   - height (constant? so no need)
   - maxBats
   - batSpawnRate
   - list of bats
        - x, y coordinates
   - isSpawningBats? maybe
