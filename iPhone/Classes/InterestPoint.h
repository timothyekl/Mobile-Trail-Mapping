#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface InterestPoint : NSObject {

    NSInteger _id;
    CGPoint _location;
	NSString* _category;
	NSString* _title;
    NSString* _summary;
    UIColor* _color;
    NSInteger _categoryID;
}

@property (nonatomic) NSInteger id;
@property (nonatomic) CGPoint location;
@property (nonatomic, retain) NSString* category;
@property (nonatomic, retain) NSString* title;
@property (nonatomic, retain) NSString* summary;
@property (nonatomic, retain) UIColor* color;
@property (nonatomic) NSInteger categoryID;

-(id) initWithParams:(NSInteger)id location:(CGPoint)p category:(NSString *)c title:(NSString *)t summary:(NSString *)s;

@end
