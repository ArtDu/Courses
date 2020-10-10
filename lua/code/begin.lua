#!/usr/bin/lua

print("Hello World")

------------

-- определяет функцию факториала
function fact (n)
    if n == 0 then
        return 1
    else
        return n * fact(n-1)
    end
end
print("enter a number:")
a = io.read("*n")        -- считывает число
print(fact(a))

----------------
a = 1
b = a*2

a = 1;
b = a*2;

a = 1; b = a*2

a = 1 b = a*2     -- уродливо, но допустимо


------------------

dofile("lib.lua")    -- загружает вашу библиотеку
n = norm(3.4, 1.0)
print(twice(n))       --> 7.0880180586677

------------------

--[[
  print(10)        -- ничего не происходит (закомментировано)
--]]


---[[
print(10)        --> 10
--]]


-----------------

print(b)    --> nil
b = 10
print(b)    --> 10

------------------

b = nil
print(b)    --> nil


--------

-- lua -i -llib -е "a = twice(4)"

--------

-- lua -e "sin=math.sin" script a b

--[[
arg[-3] = "lua"
arg[-2] = "-e"
arg[-1] = "sin=math.sin"
arg[0]  = "script"
arg[1]  = "a"
arg[2]  = "b"
]]
