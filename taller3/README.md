# TALLER 3


1. Create a directory for the project called taller3

2. Inside taller3, create directory called static-html-directory

3. Inside static-html-directory, create file called index.html and put some html tags in it.

4. Inside taller3, create Dockerfile with this content
```dockerfile
FROM nginx

COPY static-html-directory /usr/share/nginx/html
```

5. Open the console and position yourself in taller3, run the following command:   
        `docker image build -t eam .`

6. List the images to see the new image named eam, with the following command:  
        `docker image ls -a`

7. Finally, run the container to see your image running, with the following command:  
        `docker run --name taller3-web1 -d -p 8888:80 eam`

8. Open your browser at http://localhost:8888/
