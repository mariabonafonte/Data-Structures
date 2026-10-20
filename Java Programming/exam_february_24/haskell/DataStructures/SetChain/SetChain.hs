-- Student's name:
-- Student's group:
-- Identity number (DNI if Spanish/passport if Erasmus):

module DataStructures.SetChain.SetChain (
    SetChain
    , empty
    , isEmpty
    , pendingTransactions
    , add
    , getEpoch
    , size
    , validate
    -- 
    , addAll    
    , fold
    , toList
    ) where

import qualified DataStructures.Set.LinearSet as S
import qualified DataStructures.Dictionary.AVLDictionary as D
import Data.Bool (Bool(False))

--                    Mempool            History                 Epoch
data SetChain a = SC (S.Set a) (D.Dictionary Integer (S.Set a)) Integer

-- -------------------------------
-- DO NOT MODIFY THE CODE ABOVE
-- -------------------------------

-- * Exercise a)
empty :: SetChain a
empty = SC S.empty D.empty 0

-- * Exercise b)
isEmpty :: SetChain a -> Bool
isEmpty (SC set dict _) = S.isEmpty set && D.isEmpty dict

-- * Exercise c)
getEpoch :: (Eq a) => a -> SetChain a -> Integer
getEpoch tx (SC _ dict _) =
  case filter (\(epoch, set) -> S.isElem tx set) (D.keysValues dict) of
    ((epoch, _):_) -> epoch
    [] -> -1

--case filter (S.isElem tx . snd) (D.keysValues dict) of
    --((epoch, _):_) -> epoch
    --[] -> -1

-- * Exercise d)
size :: SetChain a -> Int
size (SC _ dict _) = sum(map S.size (D.values dict))

-- * Exercise e)
pendingTransactions :: SetChain a -> Bool
pendingTransactions (SC mempool _ _) = not(S.isEmpty mempool)

-- * Exercise f)
add :: (Eq a) => a -> SetChain a -> SetChain a
add t (SC mempool history epoch) = SC (S.insert t mempool) history epoch

-- * Exercise g)
validate :: (Eq a) => SetChain a -> SetChain a
validate (SC mempool history epoch)
  | S.isEmpty mempool = SC mempool history epoch
  | any (\set -> any(\x -> S.isElem x set)(S.elements mempool)) (D.values history) = 
        error "transaction already validated"
  | otherwise = SC S.empty (D.insert epoch mempool history) (epoch + 1)

-- * Exercise h)
addAll :: (Eq a) => [a] -> SetChain a -> SetChain a
addAll lista sc = foldr add sc lista

-- * Exercise i)
fold :: (a -> b -> b) -> b -> SetChain a -> b
fold f ini (SC mem history ep) = foldr (\set acc -> S.fold f acc set) ini (D.values history)

-- * Exercise j)
toList :: SetChain a -> [a]
toList sc = fold (:) [] sc

-- -------------------------------
-- DO NOT MODIFY THE CODE BELOW
-- -------------------------------

instance (Show a) => Show (SetChain a) where
  show (SC s h e) = concat ["SetChain(", show s, ", ", show h, ", ", show e, ")"]

instance (Eq a) => Eq (SetChain a) where
  (SC m1 h1 e1) == (SC m2 h2 e2) = m1 == m2 && h1 == h2 && e1 == e2

  