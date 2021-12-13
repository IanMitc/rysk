import React from 'react';
import { Button, Col, Container, container, Form, form, Row } from 'react-bootstrap';

const Login = () => {
    return (
        <>
            <Container className="at-5">
                <Row>
                    <Col lg={4} md={6} sm={12}>
                        <Form>
                            <Form.Group className="mb-3" controlId="formBasicEmail">
                                <Form.Control type="email" placeholder="Enter email" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formBasicPassword">
                                <Form.Control type="password" placeholder="Password" />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicCheckbox">
                                <Form.Check type="checkbox" label="Check me out" />
                            </Form.Group>
                            <Button variant="primary btn-block" type="submit">
                                Login
                            </Button>
                        </Form>
                    </Col>
                </Row>
            </Container>
        </>
    )
}