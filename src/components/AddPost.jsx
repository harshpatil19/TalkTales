import { useEffect, useRef, useState } from "react";
import { loadAllCategories } from "../Services/Category-service";
import {
  Button,
  Card,
  CardBody,
  Container,
  Form,
  Input,
  Label,
} from "reactstrap";
import JoditEditor from "jodit-react";
import { createPost as doCreatePost } from "../Services/post-service";
import { getCurrentUserDetail } from "../Auth";

const AddPost = () => {
  const editor = useRef(null);
  const [content, setContent] = useState("");
  const [categories, setCategories] = useState([]);
  const [user, setUser] = useState(undefined);

  const [post, setPost] = useState({
    title: "",
    content: "",
    categoryId: "",
  });

  useEffect(() => {
    const loggedInUser = getCurrentUserDetail();
    if (loggedInUser) {
      setUser(loggedInUser);
    } else {
      console.error("No user found in localStorage. Redirecting to login.");
    }

    loadAllCategories()
      .then((data) => {
        console.log(data);
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  const fieldChanaged = (event) => {
    setPost({ ...post, [event.target.name]: event.target.value });
  };
  const contentFieldChanged = (data) => {
    setPost({ ...post, content: data });
  };

  const stripHTMLTags = (html) => {
    const doc = new DOMParser().parseFromString(html, "text/html");
    return doc.body.textContent || "";
  };
  const createPost = (event) => {
    event.preventDefault();

    if (!user || !user.id) {
      alert("User is not logged in. Please log in first.");
      return;
    }

    post["userid"] = user.id;
    const plainContent = stripHTMLTags(post.content);
    post.content = plainContent;
    doCreatePost(post)
      .then((data) => {
        alert("Post created successfully!");
        // Reset the form or update UI accordingly
      })
      .catch((error) => {
        alert("Error creating post.");
        console.error(error);
      });
  };

  return (
    <div className="wrapper">
      <Card className="shadow-sm border-0 mt-2">
        <CardBody>
          <h3>What going in your mind ?</h3>
          <Form onSubmit={createPost}>
            <div className="my-3">
              <Label for="title">Post title</Label>
              <Input
                type="text"
                id="title"
                placeholder="Enter Here"
                className="rounded-0"
                name="title"
                onChange={fieldChanaged}
              />
            </div>

            <div className="my-3">
              <Label for="content">Post Content</Label>

              {/* <Input
                type="textarea"
                id="content"
                placeholder="Enter Here"
                className="rounded-0"
                style={{ height: "300px" }}
              />*/}
              <JoditEditor
                ref={editor}
                value={post.content}
                onChange={contentFieldChanged}
                config={{
                  readonly: false, // Allow rich text
                  toolbar: true,
                  askBeforePaste: false, // Disable the "Paste as HTML" dialog
                  cleanOnPaste: true,  // Enable toolbar
                }}
              />
            </div>

            <div className="my-3">
              <Label for="category">Post Category</Label>
              <Input
                type="select"
                id="category"
                placeholder="Enter Here"
                className="rounded-0"
                name="categoryId"
                onChange={fieldChanaged}
                defaultValue={0}
              >
                <option disabled value={0}>
                  --Select category--
                </option>
                {categories.map((category) => (
                  <option key={category.categoryId} value={category.categoryId}>
                    {category.categoryTitle}
                  </option>
                ))}
              </Input>
            </div>
            <Container className="text-center">
              <Button type="submit" className="rounded-0" color="primary">
                Create Post
              </Button>
              <Button className="rounded-0 ms-2" color="danger">
                Rest Content
              </Button>
            </Container>
          </Form>
        </CardBody>
      </Card>
    </div>
  );
};
export default AddPost;
