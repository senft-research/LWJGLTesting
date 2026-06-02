# Hello Triangle, Goodbye Mega-Classes!
So, after getting an actual window created, I began to focus on the actual rendering logic of OpenGL. This meant I needed
to successfully render a simple, plain, triangle onto the screen. To do this I would need to use several concepts. 

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

## The Rendering Pipeline
So, in very simple terms, the Rendering of an object onto the screen works in the following manner: 

- Vertex Specification (The buffers discussed before)
- Optional Steps (Not done yet)
- Fragmentation Shader

This is where I actually had the easiest time, as it was reasonably trivial to understand the steps due to my past experiences
using Unity. Unity utilises Shaders, but in a highly abstracted way (A lot of the "meat and potatoes" has been done behind
the scenes so the User doesn't need to worry about it). That said, I still took note of the way Shaders worked in OpenGL as, 
like the one of my favourite sayings goes, "To assume makes an arse out of you and me".

I could assume I knew everything to do with Shaders (I've used them to make cool effects in Game Jams, I've used them to
make procedurally generating terrain pretty in proof of concepts etc.) but to do so is a pitfall of learning. The best rule
of thumb I have found when learning something new, is "assume all of it is new until it has convinced you otherwise". This
was a vindicated mindset when I found out about the difference between Vertex and Fragmentation Shaders. 

- Vertex Shaders handle the geometry of the rendered model
- Fragmentation Shaders handle the colour and effects of a model

This is slightly different to the universal "Shader" found in Unity, that typically only acts as an effect (focusing a lot
more on the fragmentation side of things). But, as an admission, I am **still not confident** in these definitions. In fact, 
I had to relook at my notes to be able to properly explain the difference in a concise manner. I hope this demonstrates that,
even with weeks of practice behind me, I am still open to admitting I still have more to learn. This is a key admission 
learners should be brave enough to make, if they want to truly progress their understanding of new concepts.

## Tidying the code = Tidying your understanding
So, once I had understood the basic concepts, it only took an hour or so to actually get a triangle rendered. The actual
code to do so is _relatively_ simple, but a little involved / tedious. The thing I focused on after this was actually 
"abstracting" my code out into classes. The main reason for this was not only just to tidy up the code, but also to see 
if I actually understood what I had learned. 

For example, when creating my `Window` class, I actually didn't add all the logic that inherently belongs to the "Window"
in my first iteration of it. This was made obvious by left over code in my main "mega class" after I had done the abstractions.
Using abstraction of logic to see if you understand the code you have written fully is a great sanity check when you have 
finished learning a subject.

Eventually, after some work, I landed on the following abstractions: 
- Window: Handles the Logic of presenting the viewport / window to the user.
- Mesh: Handles the Vertex data of whatever I am attempting to render.
- Shader: Handles the processing of the Mesh via a Vertex and Fragmentation Shader.
- Texture: Handles the Texture (if applicable) to be applied to a Mesh.

These are not perfect abstractions and, in turn I actually found myself adding to, removing from, and separating out some
of the logic from these initial classes as my learning continued. In this educator's opinion, the "open-closed" principle
does not apply when you are solely learning a project. You must not lock in to your initial assumptions, if you discover
said assumptions are wrong.

## Final thoughts and an admission of guilt...
So this log was generally me finally getting to grips with the meat and potatoes of OpenGL, and finally achieving the rendering
of my first triangle and basic concepts. However, if I had just approached this as "I want a pretty triangle" I would have 
likely learned a lot less than I did. 

Nothing would have stopped me from just copy-pasting code from the examples or, if even lazier, asking an LLM to do it for
me. But I would have learned next to nothing. And this is not just hyperbole... I have an admission to make. A few years ago
I tried learning LWJGL before, during a time in my life where I used LLMs quite heavily. I managed to get all the way to 
rendering a triangle and square on the screen via abstractions for a hypothetical Java Game Engine. It worked, and it was
reasonably smooth... **But I learned nothing**. 

And I genuinely mean this, I managed to go further than I did with this learning experience so far, with moving renders 
and much better abstractions. And yet, when I started learning this time around, essentially every single concept was new 
to me... Let that sink in... "I" made a Java Game Engine, on top of LWJGL. I had moving renders on the screen, and yet did
not recall / understand even something as basic as "What is a Vertex Buffer Object" or "Why do I need a Fragmentation Shader?". 

This is why I tend to be so against LLMs as a teaching tool. Because even to this day I swear that, at the time, I did
not consider what I was doing as "vibe coding". I thought I was just using the LLM as a tool to help sort out some of the
boilerplate of the project. In reality, even though I **thought** I knew the process / structure, in reality I couldn't
even recognise what I hadn't learned. That is the trap of Vibe-Coding, it forces you into the "Dunning-Kruger Effect", the
fallacy of overestimating your knowledge of a subject when you are still relatively new to it.

This goes beyond LLMs. Blindly following tutorials, not looking into deeper concepts, caring more about the product than the 
process. All of these things lead to you not realising what you do not know. To bring this full circle, you ironically become
a victim of the curse of knowledge, as you yourself get tricked into thinking you understand the basics of a concept. 

So with that in mind, when learning a new concept, go into it with the mantra I approach life in general with... "assume you 
are an idiot, and you know nothing, and do everything in your power to change that".
