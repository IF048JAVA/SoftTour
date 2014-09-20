<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!-- Form -->

    <div id="left_right_container">
        <div id="left_div">
            <form method="post" ectype="text/plain" action="mailto:yurivalkiv@gmail.com" name="form"
                  onsubmit="return checkForm('user_name','user_email','user_area')">
                <p class="form_p">
                    <legend>Форма зворотнього зв'язку</legend>
                <p class="form_p"><input type="text" id="user_name" class="input_overall" name="user_name"
                                         placeholder="Введіть Ваше ім'я:"/></p>

                <p class="form_p"><input type="text" id="user_email" class="input_overall" name="user_email"
                                         placeholder="Введіть Ваш e-mail:"/></p>

                <p class="form_p"><select name="user_select" id="user_select" class="input_overall" size="1">
                    <option value="1" class="option_style">Технічна несправність сайту</option>
                    <option value="2" class="option_style">Оформлення сайту</option>
                    <option value="3" class="option_style">Інше</option>
                </select></p>
                <p class="form_p"><textarea name="user_area" id="user_area" class="input_overall" wrap="virtual"
                                            placeholder="Коментар:" style="height:100px"></textarea></p>

                <p class="form_p"><input type="submit" name="user_submit" class="submit_button" value="Надіслати"/></p>
            </form>
        </div>

        <div id="right_div">
            <span><img src="img/image1.jpg" class="user_img"></span><br>
            <span><img src="img/image2.jpg" class="user_img"></span><br>
            <span><img src="img/image3.jpg" class="user_img"></span><br>
            <span><img src="img/image4.jpg" class="user_img"></span><br>
            <span><img src="img/image5.jpg" class="user_img"></span><br>
        </div>
    </div>




