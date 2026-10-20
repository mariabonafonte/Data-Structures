module Demos.SetChain.SetChainDemo where
    
import DataStructures.SetChain.SetChain

sc1 :: SetChain Int
sc1 = validate (add 1 (add 2 (add 3 empty)))
-- SetChain(LinearSet(), AVLDictionary(0->LinearSet(3,2,1)), 1)

e2_1 = getEpoch 2 sc1
-- 0

e7_1 = getEpoch 7 sc1
-- -1

s1 = size sc1
-- 3

sc2 :: SetChain Int
sc2 = addAll [6,4,5,7] sc1
-- SetChain(LinearSet(7,5,4,6), AVLDictionary(0->LinearSet(3,2,1)), 1)

e3_2 = getEpoch 3 sc2
-- 0

e7_2 = getEpoch 7 sc2
-- -1

s2 = size sc2
-- 3

sc3 :: SetChain Int
sc3 = validate sc2
-- SetChain(LinearSet(), AVLDictionary(0->LinearSet(3,2,1),1->LinearSet(7,5,4,6)), 2)

e6_3 = getEpoch 6 sc3
-- 1

e11_3  = getEpoch 11 sc3
-- -1

sc4 :: SetChain Int
sc4 = addAll [8,7,6] sc3
-- SetChain(LinearSet(6,7,8), AVLDictionary(0->LinearSet(3,2,1),1->LinearSet(7,5,4,6)), 2)

tl4 = toList sc4
-- [3,2,1,7,5,4,6]

s = fold (+) 0 sc4
-- 28

p = fold (*) 1 sc4
-- 5040

sc5 :: SetChain Int
sc5 = validate sc4
-- throws an error because transaction 6 is already validated


