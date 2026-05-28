# Hello Triangle, Goodbye Mega-Classes!
So, after getting an actual window created, I began to focus on the actual rendering logic of OpenGL. This meant I needed
to succesfully render a simple, plain, triangle onto the screen. To do this I would need to use several concepts. 

Specifically, I would need to learn about buffers, VBOs, VAOs, and EBOs. Now, if you read the 3 acronyms and got confused,
don't worry, you are likely not alone! A lot of people, especially those who are more experienced with a complex topic,
will tend to do things such as this. Using acronyms without explaining what they mean, assuming you know "basic" concepts etc.
This phenomenon actually has a name...

## The Curse of Knowledge
The Curse of Knowledge is an insidious thing in Education. It is the idea that, when someone understands a specialized 
subject, they are likely to assume other people share that understanding. This is incredibly damaging to those who try to
teach. I know many of those reading will have encountered a situation where a teacher, lecturer, tutor, or other educator
has said something similar to "To do this next part, we need to X, but that's simple stuff, you should know that already
so we will skip it!". That is the Curse of Knowledge in practice! 

So why do I bring it up here? It is because, when learning a lot of new concepts in one go, it is critical to build on 
each one in a "narratively cohesive" way. That is to say, if the first concept cannot be directly linked to the next
concept, then you are going to struggle to understand what you are learning. Note though that I did not use the word "remember",
but rather understand, this is intentional. "Memorising" is the ultimate fallacy of learning. It was Richard Feynman (
not, as some people believe, Einstein) who once suggested that, if you cannot explain a concept to a 6-year-old, 
you do not truly understand the concept. As such, you have to actively be searching for instances of the "Curse of Knowledge"
as you learn, because it is almost guaranteed that the creator of the content won't spot all instances of it.

## VBO, VAO, EBO, I DunnO...
So, when looking into the basics of rendering things within the Window, I came across 4 main concepts I would need to utilise
to get the render working. 

- Buffers
- Vertex Buffer Object (VBO)
- Vertex Array Object (VAO)
- Element Buffer Object (EBO)

Now do not get me wrong, I could have easily just copy-pasted the code from the tutorials, and got a triangle working with,
relatively, not much effort. However, this would be completely useless to me. It is one of the most significant issues I
find with a lot of people learning new things nowadays... they just want results. Without a decent understanding of what
each of these concepts is / how they work together, the ability to draw a triangle on the screen is not going to help you
when you want to render rockets components in 3D with full lighting and textures!

So, just as in the previous log, I refused to write any code until I understood these 4 concepts, introduced in the "hello triangle"
section of the LearnOpenGL guide. 

In short:

- Buffers are objects that manage some of the memory of the GPU. It is basically a way to tell the GPU "This part of your memory is for this type of data now, cheers!"

- VBOs are **Buffer** Objects specifically for storing vertex data. It does not have a set method per-say, but is rather a buffer that is assigned specifically to be for vertex data.

- VAOs are essentially a collection of "rules" (attribute pointers) that tell the OpenGL how to read vertex data found in a VBO. This prevents having to respecify the rules each time you want to read a different VBO.

- EBOs are buffer objects for storing indices that can be used to tell OpenGL what vertices (from a VBO) to draw. This is great for reducing the amount of vertices drawn.

These concepts are fundamental to understanding how OpenGL works in terms of rendering. VBOs store the vertex data (individual points), 
VAOs store the rules of how to read the data in VBOs, and EBOs are used to reduce the amount of vertices you need to store in the VBO. 
If you did not understand these concepts, you would leave the tutorials knowing how to write pointers, VBOs, VAOs etc. But 
you would have almost 0 clue as to when, where and why to use them!

As a side note, it is important to understand that the LearnOpenGL tutorial, whilst great overall, actually does not 
explain buffers at all in the introductory sections. When I reached the first discussion of buffers, I was quite confused
due to this. If I had been coding along as I read, I would now have a bunch of contextless nonsense. But, because I read
the content before acting on it, I was able to focus some of my time to identifying "oh... they don't actually explain buffers" 
here. After a bit of searching I did find a buffers explanation... **In the Advanced Data section!** This is a classic 
example of the Curse of Knowledge, with the author of this guide thinking that the basic explanation of what a Buffer is
was not important, and the reader could worry about it well into the "advanced" concepts.

Call me crazy, but I am pretty sure that the **idea a buffer stores things in memory in a specific way on the GPU** is not
that hard of a concept to describe in a sentence or two... in fact... I just did! Yet, if one was to follow this tutorial
to the letter, without taking a step back and considering the Curse of Knowledge, it would be **27 sections** before you
get an explanation. Hilariously, the concept of a framebuffer is explained in its own section before the general concept of
a buffer is explained!

## Tidying the code = Tidying your understanding
So, once I had understood the basic concepts, it only took an hour or so to actually get a triangle rendered. The actual
code to do so is _relatively_ simple, but a little involved / tedious. The thing I focused on after this was actually 
"abstracting" my code out into classes. The main reason for this was not only just to tidy up the code, but also to see 
if I actually understood what I had learned. 

For example, when creating my `Window` class, I actually didn't add all the logic that inherently belongs to the "Window"
in my first iteration of it. This was made obvious by left over code in my main "mega class" after I had done the abstractions.
Using abstraction of logic to see if you understand the code you have written fully is a great sanity check when you have 
finished learning a subject.
