post '/category/add' do
  category = Category.first_or_create(:name => params[:category])
  "Added Category #{category.name}"
end
