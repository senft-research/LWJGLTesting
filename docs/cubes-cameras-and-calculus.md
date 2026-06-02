# Cubes, Comments, Cameras, and Calculus
So, after getting the first Triangles (coloured and textured) working, it was time to dive into 3D rendering. This would be
quite the step-up for myself, as it would mean having to handle objects with a ridiculous amount of vertices. Then, I would
need to find a way to easily move these massive arrays around the "render space", as I colloquially called it in my head,
simultaneously.

I will admit, before I went in, I was a bit nervous, as I was sure this is where I was going to "bounce off" of OpenGL,
if anywhere. I have never been more wrong in my entire life... so far haha! 

## Cubes and Comments
So, first things first, was I needed to make a Cube. This is something that I was not looking forward to as, due to the 
concepts learned in previous chapters, I had a rough idea in my head of what I was going to see next in the tutorial content.
Indeed, I had a mixture of satisfaction and dread as I saw the example vertices array of the cube. Satisfaction, as it was 
exactly how I thought it would be, hence being an indicator I had actually learned the previous concepts correctly. Dread,
because it _was exactly how it thought it would be_ ... specifically, this monstrosity... 

```java
 float[] cubeVertices = {
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f, 0.0f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  1.0f, 1.0f, 0.0f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.0f, 1.0f, 1.0f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f,  0.5f,  0.5f, 0.5f, 0.5f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.5f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.7f, 0.9f,   0.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.3f, 0.8f, 0.3f,   1.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   0.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.1f, 0.6f, 0.1f,   1.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  0.7f, 0.2f, 0.7f,   0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.9f, 0.2f, 0.9f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  0.2f, 0.9f, 0.2f,   0.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f
    };
```

At this point I had... [a reaction](https://www.youtube.com/watch?v=umDr0mPuyQc).

Probably a reaction a lot of you had seeing this for the first time. My dear readers, this is a cube. Specifically, the 
vertices of a cube. The first 3 floats are a Vertex position, the next 3 are colour data, and the last 2 are texture
coordinates. This said, the way it presented is frankly horrible. I cannot tell where one faces ends, another begins, it
isn't clear what each row represents. 

This is one of these rare times where I will comment my code, if only for understanding. I am a believer (sometimes to a
fault) in the idea of "Clean Code", the idea that having lots of comments in your code is not preferable to the code being
self-explanatory. Due to that, outside of learning, I try to keep commenting to a minimum where possible. That said, whilst
learning, I tend to the opposite.

I have a very strict "wake up tomorrow" policy when it comes to my note-taking. "If I am going to struggle to remember 
what I wrote here is tomorrow, I need to make better notes". In this case, a 36 line monstrosity of an Array is something
I am almost guaranteed to forget by the next day. Hence, I added minimal, but crucial comments to the array, and the one
you can see in the actual project looks like this:

```java
    float[] cubeVertices = {
            // Each of these sets of vertices represent a different face of the cube, with EBOs not being used.

            // back face
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f, 0.0f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  1.0f, 1.0f, 0.0f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f, 0.0f,   0.0f, 0.0f,

            // front face
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.0f, 1.0f, 1.0f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
            -0.5f,  0.5f,  0.5f,  0.5f, 0.5f, 0.5f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f, 1.0f,   0.0f, 0.0f,

            // left face
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.5f, 0.0f, 1.0f,   1.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.2f, 0.8f, 0.2f,   0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.7f, 0.9f,   0.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 0.5f, 0.0f,   1.0f, 0.0f,

            // right face
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.3f, 0.8f, 0.3f,   1.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.3f, 0.3f, 0.8f,   0.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   0.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.8f, 0.3f, 0.3f,   1.0f, 0.0f,

            // bottom face
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.1f, 0.6f, 0.1f,   1.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.1f, 0.1f, 0.6f,   1.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  0.7f, 0.2f, 0.7f,   0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.6f, 0.1f, 0.1f,   0.0f, 1.0f,

            // top face
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.9f, 0.2f, 0.9f,   1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.9f, 0.9f, 0.2f,   1.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  0.2f, 0.9f, 0.2f,   0.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.2f, 0.9f, 0.9f,   0.0f, 1.0f
    };
```
I did not explain the position, colour, and texture coordinates, as I was already quite confident in them. I was confident as,
when writing this section of the log, I was able to explain each float value from memory, without looking it up. If I was 
not able to do so, I would have almost certainly added said notes to the array. In fact, early into the project I did,
whilst I was still getting to grips with the concept. 

However, sometimes notes just simply need to be there. Whilst I know that each set of 6 vertices represents a cube face,
being able to parse out those groups of 6 rows, when they are all together in one 36 line array, is not a nice experience.
Hence, the comments remain as, just because you understand a concept, does not mean it isn't difficult to realise it is 
something you understand at first glance. 

To conclude, there will never will there be a time I look at the uncommented version of this array, and be able to separate 
each face's vertices in my head. Hence, there will never be a time when I will be confident enough to remove those comments. 

## 
